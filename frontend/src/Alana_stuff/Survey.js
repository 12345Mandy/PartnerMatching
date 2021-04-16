import Question from "./Question";
import React, {useState, useEffect} from 'react';
import {Link} from 'react-router-dom'
import firebase from "firebase";
import axios from "axios";
import fire from '../fire'
import "./Survey.css"

function Survey(props) {
    const [title, setTitle] = useState("loading...");
    const [surveyCreator, setCreator] = useState(""); // use this to check if button should show up
    const [description, setDescription] = useState("almost there...");
    const [questions, setQuestions] = useState([]);
    const [userAnswers, setUserAnswers] = useState([]);


    const db = firebase.firestore();
    const currentPoll = props.uniqueID;

    // load in a survey - hardcoded to lab partner survey by default
    const loadSurvey = async () => {
        const doc = await db.collection("surveys").doc(currentPoll).get();
        setTitle(doc.data().title);
        setCreator(doc.data().creator); // sets creator --> if current user matches this, display button to get results
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

        // makes the document id the same as the user id and also overrides previous changes if the user
        // submits twice
        let responsesRef = db.collection("surveys").doc(currentPoll).collection("responses");

        await responsesRef.doc(firebase.auth().currentUser.uid).set({
            userID: firebase.auth().currentUser.uid,
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

    const createSurvey = (creator, description, title) => {
        db.collection("surveys").doc().set({
                creator: creator,
                description: description,
                title: title
            }
        )
            .then(() => console.log("Created survey!"))
            .catch((error) => {
                console.error("Error making survey: ", error);
            });
    }

    // checking if user is admin is hard coded in --> will be used to display button for survey results.
    if (surveyCreator === firebase.auth().currentUser.uid) {
        console.log(db.collection("surveys").doc(currentPoll).id)
        return (<div>
                <div className="poll">
                    <div className="surveyInfo">
                        <h1>{title}</h1>
                        <p>{description}</p>
                    </div>
                    {questions.map((q, qid) =>
                        <Question options={q.options} question={q.question} id={qid} onSelect={setAnswerFromChild}/>
                    )}
                    <button type="button" onClick={submitSurvey}>submit</button>

                </div>
                <Link to={`/ViewResults/${currentPoll}`} className="poll">Check Results -- Generate Pairs</Link>
            </div>
        )
    } else {
        console.log("yes")
        console.log(surveyCreator)
        return (
            <div className="poll">
                <div className="surveyInfo">
                    <h1>{title}</h1>
                    <p>{description}</p>
                </div>
                {questions.map((q, qid) =>
                    <Question options={q.options} question={q.question} id={qid} onSelect={setAnswerFromChild}/>
                )}
                <button type="button" onClick={submitSurvey}>submit</button>

            </div>
        );
    }
}

export default Survey;