import Option from "./Option";
import DisplayPerson from "./DisplayPerson";
import DisplayPair from "./DisplayPair.js";
import React, {useState, useEffect, useRef} from 'react';
import axios from "axios";
import firebase from "firebase";

function SurveyAdmin(props) {
    const [surveyCreator, setCreator] = useState(""); // use this to check if button should show up

    const [displayResults, setDisplayResults] = useState(false);

    const [title, setTitle] = useState("loading...");
    const [results, setResults] = useState([])
    const [pairs, setPairs] = useState([])
    const db = firebase.firestore();
    // EDIT THIS DEPENDING ON WHAT SURVEY IS NEEDED.
    const currentPoll = props.uniqueID;

    // on page load, load in the survey
    useEffect(() => {
        loadInfo();
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

    const onPairClicked = () => {
        console.log("Temp has: " + results);
        console.log(results);


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
            </div>
        );
    } else {
        // console.log(pairs)
        // const getPairs = async () => {
        //     let pairs = await db.collection("surveys").doc(currentPoll).collection("pairs")
        //         .doc("generatedPairs").get().then(snap =>snap.data())
        //    console.log(pairs[0])
        // }
        // getPairs()
        return (
            <div className="poll">
                <h1>{title}</h1>
                <button onClick={onPairClicked}>Your Current Pair</button>
                <div>
                    {/*{ db.collection("surveys").doc(currentPoll).collection("pairs")*/}
                    {/*    .doc("generatedPairs").get().then(snap => snap.exists) &&*/}

                    {/*    <div>*/}
                    {/*        You have been paired with:*/}
                    {/*        pairs[0]*/}

                    {/*    </div>*/}
                    {/*}*/}
                    ${
                        pairs[firebase.auth().currentUser.uid.toString()]
                    }
                </div>
                ur not admin
            </div>
        );
    }

}


export default SurveyAdmin;