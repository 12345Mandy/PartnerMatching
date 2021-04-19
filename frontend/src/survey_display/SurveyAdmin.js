import DisplayPerson from "./DisplayPerson";
import DisplayPair from "./DisplayPair.js";
import React, {useEffect, useState, useRef} from 'react';
import axios from "axios";
import firebase from "firebase";
import UserDisplayPair from "./UserDisplayPair";

function SurveyAdmin(props) {
    const surveyCreator = useRef(null)

    const [displayResults, setDisplayResults] = useState(false);
    const [title, setTitle] = useState("loading...");
    const [results, setResults] = useState([])
    const [pairs, setPairs] = useState({})

    const [userData, setUserData] = useState({});
    const [partnerData, setPartnerData] = useState([]);

    const [idToName, setIDToName] = useState({});

    const db = firebase.firestore();

    const currentPoll = props.uniqueID;

    // on page load, load in the survey
    useEffect(() => {
        loadInfo();
        updateDisplayResults().then(results => {
            const currUser = firebase.auth().currentUser.uid;
            console.log(currUser);
            console.log(surveyCreator);
            if (results && currUser !== surveyCreator.current) {
                // initializes the pairs for users if they're ready
                db.collection("surveys").doc(currentPoll).collection("pairs")
                    .doc("generatedPairs").get().then(d => {
                    let pairs = d.data();

                    let partnerList = pairs.pairs[currUser];
                    let partDataList = [];

                    // if partner doesn't exist, don't get partner data
                    if(partnerList !== undefined) {
                        for (let i = 0; i < partnerList.length; i++) {
                            db.collection("surveys").doc(currentPoll).collection("responses")
                                .doc(partnerList[i]).get().then(a =>
                                partDataList.push(a.data()));
                        }

                        setPartnerData(partDataList);
                    }


                    db.collection("surveys").doc(currentPoll).collection("responses")
                        .doc(currUser).get().then(a =>
                        setUserData(a.data()))
                })
            }
            setDisplayResults(results);
        })
    }, [])


    const generatePairs = async () => {
        let allQuestions = (await db.collection("surveys").doc(currentPoll).collection("questions").orderBy("questionnumber").get()).docs.map(d => d.data());
        let answers = (await db.collection("surveys").doc(currentPoll).collection("responses").orderBy("userID").get()).docs.map(d => d.data());

        // assumes that responses array has the index represent question id and value represent which answer
        // was picked
        const toSend = {
            questions: allQuestions,
            answers: answers
        };

        console.log(answers);

        let config = {
            headers: {
                "Content-Type": "application/json",
                'Access-Control-Allow-Origin': '*',
            }
        };

        axios.post(
            "http://localhost:4567/match",
            toSend,
            config
        ).then(async response =>  {
            console.log(response.data)
            setPairs(response.data["pairs"])
            console.log(response.data["pairs"]);

            await db.collection("surveys").doc(currentPoll).collection("pairs")
                .doc("generatedPairs").set(
                    {pairs: response.data["pairs"]}
                )

            let idToNameTemp = {};
            for (const [key, value] of Object.entries(response.data["pairs"])) {
                let nameKey = await getNameFromUserID(key);
                let nameValue = []
                for (let i = 0; i < value.length; i++) {
                    nameValue.push(await getNameFromUserID(value[i]));
                }
                idToNameTemp[nameKey] = nameValue;
            }

            console.log(idToNameTemp);
            setIDToName(idToNameTemp);
        }).catch(error => {
            console.log(error);
            alert("Oops, something went wrong.");
        });
    }

    // sets any info needed for survey
    const loadInfo = async () => {
        const doc = await db.collection("surveys").doc(currentPoll).get();
        surveyCreator.current = doc.data().creator; // sets creator --> if current user matches this, display button to get results
        setTitle(doc.data().title);
        let temp = []
        const responses =
            (await db.collection("surveys").doc(currentPoll).collection("responses").orderBy("userID").get()).docs.map(d => d.data());
        responses.forEach(user => {
            temp.push(user)
        })

        setResults(temp)
    }

    const updateDisplayResults = async () => {
        return await db.collection("surveys").doc(currentPoll).collection("pairs")
            .doc("generatedPairs").get()
            .then(a => a.get("pairs") !== undefined).catch(error =>
                console.log(error));
    }

    const deleteSurvey = () => {
        let input = prompt("Type DELETE to delete this survey.");
        if (input === "DELETE") {
            const thisSurvey = db.collection("surveys").doc(currentPoll);

            // apparently this doesn't delete subcollections so welp
            thisSurvey.delete().then(x => {
                alert("Survey Successfully Deleted!")
                window.location.replace("http://localhost:3000/Homepage")
            });
        }
    }

    const deleteResponse = () => {
        let input = prompt("Type DELETE to delete your response to this survey.");
        if (input === "DELETE") {
            const userResponseRef = db.collection("surveys").doc(currentPoll).collection("responses")
                .doc(firebase.auth().currentUser.uid);

            // apparently this doesn't delete subcollections so welp
            userResponseRef.delete().then(x => {
                alert("Survey Successfully Deleted!")
                window.location.replace("http://localhost:3000/Homepage")
            });
        }
    }

    const getNameFromUserID = async (userID) => {
        console.log("Curr ID is: " + userID);
        const responsesRef = db.collection("surveys").doc(currentPoll).collection("responses");
        const ref = (await responsesRef.doc(userID).get());
        return ref.get("name");
    }

    if (surveyCreator.current === firebase.auth().currentUser.uid) {
        return (
            <div className="poll">
                <h1>{title}</h1>
                <br/><br/>
                <h2><b>Current Survey Results</b></h2>
                <div className="surveyResultsList">
                    {results && results.map(user => {
                        return (<DisplayPerson
                            name={user.name}
                        />);
                    })}
                </div>
                <br/>
                <button type="button" onClick={generatePairs} className="pairsButton">Click for Pairs</button>
                <div>
                    {pairs && Object.entries(idToName).map(([key, value]) => {
                        return (<DisplayPair
                            user1={key}
                            matches={value}
                        />);
                    })}
                </div>
                {/*<button type="button" onClick={deleteSurvey}  className="deleteButton">Delete This Survey</button>*/}
                <div onClick={deleteSurvey}  className="deleteButton">Delete This Survey</div>
            </div>
        );
    } else {

        // only displays results when everything is ready
        if (displayResults && partnerData !== undefined && Object.keys(partnerData).length !== 0) {
            console.log(pairs)
            return (
                <div className="poll">
                    <h1>{title}</h1>

                    <UserDisplayPair
                        userData={userData}
                        partnerData={partnerData}
                        db={db}
                        currPoll={currentPoll}
                    />

                </div>
            );
        } else {
            return (
                <div className="poll">
                    <h1>{title}</h1>
                    <br/>
                    <div>
                        Survey Results Aren't Ready Yet!
                    </div>


                    <button typeof="button" onClick={deleteResponse} className="deleteButton">Delete Response</button>
                </div>
            );
        }
    }

}


export default SurveyAdmin;