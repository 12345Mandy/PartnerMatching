import Question from "./Question";
import React, {useState, useEffect} from 'react';
import {firestore} from "firebase";
import firebase from "firebase";
import axios from "axios";
import fire from '../fire'
import Option from "./Option";

// const firebaseConfig = {
//     apiKey: "AIzaSyAivvKyzEqMpc5Z8X2eZnUFkWcyCoSFS54",
//     authDomain: "survey-creator-cs32.firebaseapp.com",
//     projectId: "survey-creator-cs32",
//     name: "survey"
// };

function Survey() {
    const [title, setTitle] = useState("loading...");
    const [description, setDescription] = useState("almost there...");
    const [questions, setQuestions] = useState([]);
    const [userAnswers, setUserAnswers] = useState([]);

    // firebase.initializeApp(firebaseConfig);
    // if(!firebase.apps.length) {
    //     firebase.initializeApp(firebaseConfig);
    // } else {
    //     firebase.app();
    // }


    const db = firebase.firestore();
    const currentPoll = "labpartners";

    // load in a survey - hardcoded to lab partner survey by default
    const loadSurvey = async () => {
        const doc = await db.collection("surveys").doc(currentPoll).get();
        setTitle(doc.data().title);
        setDescription(doc.data().description);
        const ndoc = await db.collection("surveys").doc(currentPoll).collection("questions").orderBy("questionnumber").get();
        await setQuestions(ndoc.docs.map(d => d.data()));
        let answers = new Array(ndoc.docs.length);
        // store answer as -1 if user hasn't answered
        console.log(answers.length);
        for (let i = 0; i < answers.length; i++) {
            answers[i] = -1;
        }
        setUserAnswers(answers);

    }

    const sendResults = async () => {
        // make a new document for submitting
        // set user ID to be 0 for now
        // add doc to answers collection (need to figure out how to do if not exist stuff to make a responses collection in future)
        await db.collection("surveys").doc(currentPoll).collection("responses").add({
            userID: 0,
            responses: userAnswers
        });
        // should we add a timestamp?
        console.log("submitted!");
    }


    // on page load, load in the survey
    useEffect(() => {
        loadSurvey();
    }, [])


    // set ID, used in child component
    const setAnswerFromChild = (questionID, answer) => {
        if (userAnswers) {
            userAnswers[questionID] = answer;
        }
    }

    // attempt to submit survey
    const submitSurvey = () => {
        //TODO: obtain a user ID and add that to the submission
        console.log(userAnswers);

        // make sure each answer isn't null
        let valid = true;
        for (let i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] === -1) {
                valid = false;
            }
        }

        //TODO: prevent user from submitting duplicates

        if (valid) {
            console.log("attemp ting to submit...");
            sendResults();
        } else {
            // TODO: error message
            console.log("you didn't pick answer choices :(");
        }
    }

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
            let resp = response.data["pairs"];
            console.log(resp);

        }).catch(error => {
            console.log(error);
        });
    }

    return (
        <div className="poll">
            <h1>{title}</h1>
            <button type="button" onClick={generatePairs}>Click for Pairs</button>
            <br/>
            <p>{description}</p>
            {questions.map((q, qid) =>
                <Question options={q.options} question={q.question} id={qid} onSelect={setAnswerFromChild}/>
            )}
            <button type="button" onClick={submitSurvey}>submit</button>

        </div>
    );
}

export default Survey;