import DisplayPerson from "./DisplayPerson";
import DisplayPair from "./DisplayPair.js";
import React, {useEffect, useState} from 'react';
import axios from "axios";
import firebase from "firebase";
import UserDisplayPair from "./UserDisplayPair";

function SurveyAdmin(props) {
    const [surveyCreator, setCreator] = useState(""); // use this to check if button should show up

    const [displayResults, setDisplayResults] = useState(false);
    const [title, setTitle] = useState("loading...");
    const [results, setResults] = useState([])
    const [pairs, setPairs] = useState({})
    const [partnerData, setPartnerData] = useState({});

    const db = firebase.firestore();

    const currentPoll = props.uniqueID;

    // on page load, load in the survey
    useEffect(() => {
        loadInfo();
        updateDisplayResults().then(results => {
            if (results) {
                // initializes the pairs for users if they're ready
                db.collection("surveys").doc(currentPoll).collection("pairs")
                    .doc("generatedPairs").get().then(d => {
                        let pairs = d.data();
                        console.log(pairs)
                        let partner = pairs.pairs.A;
                        console.log(partner);

                    db.collection("surveys").doc(currentPoll).collection("responses")
                        .doc(partner).get().then(a =>
                            setPartnerData(a.data()));
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
        ).then(response => {
            console.log(response.data)
            setPairs(response.data["pairs"])

            db.collection("surveys").doc(currentPoll).collection("pairs")
                .doc("generatedPairs").set(
                {pairs: response.data["pairs"]}
            )
        }).catch(error => {
            console.log(error);
        });
    }

    // sets any info needed for survey
    const loadInfo = async () => {
        const doc = await db.collection("surveys").doc(currentPoll).get();
        setCreator(doc.data().creator); // sets creator --> if current user matches this, display button to get results
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

    if (surveyCreator === firebase.auth().currentUser.uid) {
        console.log("in admin");
        return (
            <div className="poll">
                <h1>{title}</h1>
                <button>Current Survey Results</button>
                <div>
                    {results && results.map(user => {
                        return (<DisplayPerson
                            name={user.userID}
                        />);
                    })}
                </div>
                <button type="button" onClick={generatePairs}>Click for Pairs</button>
                <div>
                    {pairs && Object.entries(pairs).map(([key, value]) => {
                        return (<DisplayPair
                            user1={key}
                            user2={value}
                        />);
                    })}
                </div>
                <button type="button" onClick={deleteSurvey}>Delete This Survey</button>
            </div>
        );
    } else {

        // only displays results when everything is ready
        if (displayResults && partnerData !== undefined && Object.keys(partnerData).length !== 0) {
            console.log(pairs)
            return (
                <div className="poll">
                    <h1>{title}</h1>
                    <div>
                        <UserDisplayPair
                            partnerData={partnerData}
                            db={db}
                            currPoll={currentPoll}
                        />
                    </div>

                </div>
        );
        } else
            {
                return (
                    <div className="poll">
                        <h1>{title}</h1>
                        Survey Results Aren't Ready Yet!
                    </div>
                );
            }
        }

        }


        export default SurveyAdmin;