import React, {useState, useEffect} from 'react';
import firebase from "firebase";
import NewQuestionButton from "./NewQuestionButton";
import QuestionEntry from "./QuestionEntry";
import SurveyInfoBox from "./SurveyInfoBox";
import GeneratedLink from "./GeneratedLink";
import "./SurveyCreator.css"

//TODO: delete a question or option

function SurveyCreator() {
    const [questions, setQuestions] = useState([""]);
    const [error, setError] = useState("");
    const [inputTitle, setTitle] = useState("");
    const [descr, setDescr] = useState("");
    const [customURL, setCustomUrl] = useState("");
    const [submitted, setSubmitted] = useState(false);
    const [questionOptions, setQuestionOptions] = useState([[""]]);
    const [questionImportance, setQuestionImportance] = useState([]);

    const db = firebase.firestore();

    // make a new question
    const addAQuestion = () => {
        setQuestions(questions => [...questions, ""]);
        setQuestionOptions(questionOptions => [...questionOptions, [""]]);
        console.log(questions);
    }

    // add an answer to a question
    const addOption = (qid) => {
        console.log(questionOptions);
        let copy = [...questionOptions];
        copy[qid].push("");
        setQuestionOptions(copy);
    }

    const changeQuestionContent = async (id, text) => {
        questions[id] = text;
    }

    const changeAnswerContent = async (qid, ansid, text) => {
        questionOptions[qid][ansid] = text;
    }

    const changeImportance = async (qid, imp) => {
        questionImportance[qid] = imp;
    }

    const submitSurvey = async () => {
        // copy inputs to make sure user can't change them
        let submittedQs = [...questions];
        let submittedFields = [...questionOptions];
        let submittedImp = [...questionImportance];

        // check if all fields are filled

        let noEmptyFields = true;
        for(let i = 0; i < submittedQs.length; i++) {
            if(submittedQs[i] === "") {
                noEmptyFields = false;
            }
        }

        for(let i = 0; i < submittedFields.length; i++) {
            for(let j = 0; j < submittedFields[i].length; j++) {
                if(submittedFields[i][j] === "") {
                    noEmptyFields = false;
                }
            }
        }

        for(let i = 0; i < submittedImp.length; i++) {
            if(submittedImp === "") {
                noEmptyFields = false;
            }
        }

        if(inputTitle === "" || descr === "") {
            noEmptyFields = false;
        }

        if(noEmptyFields) {
            // if valid, submit to firebase
            // make a new survey collection
            await db.collection("surveys").add({
                creator : firebase.auth().currentUser.uid,
                description : descr,
                title: inputTitle
            }).then(docRef => {
                console.log("survey added with ID: ", docRef.id);
                setCustomUrl(docRef.id);
                // add each question to the db
                for(let i = 0; i < submittedQs.length; i++) {
                    db.collection("surveys").doc(docRef.id).collection("questions").add({
                        importance: submittedImp[i],
                        questionnumber: i,
                        options: submittedFields[i],
                        question: questions[i]
                    })
                }
                setSubmitted(true);
                setError("Successfully submitted survey!");

            }).catch(error => {console.error("Error adding document: ", error);
                setError("Error submitting questions")});


        } else {
            setError("Please fill in all fields before submitting!");
        }


    }

    const updateDesc = (newDesc) => {
        setDescr(newDesc);
    }

    const updateTitle = (newTitle) => {
        setTitle(newTitle);
    }

    // on page load, make one question
    useEffect( () => {

    }, [])

    return(<div className="surveyMaker">
        <div className="surveyCreatorTitle"><h1>Create a Survey!</h1></div>
        <h3>Survey Name:</h3>
        <SurveyInfoBox className="nameBox" placeholder="survey name" change={updateTitle}/>
        <h3>Survey Description:</h3>
        <SurveyInfoBox className="descBox" placeholder="survey description" change={updateDesc}/>
        <h3>Questions:</h3>
        {questions.map((q, qid) =>
            <QuestionEntry id={qid} options={questionOptions[qid]} addAnswer={addOption} change={changeQuestionContent}
                           changeAnswer={changeAnswerContent} changeImp={changeImportance}/>)}
        <NewQuestionButton addQuestion={addAQuestion}/>
        <button className="submitButton" type="button" onClick={submitSurvey}>submit</button>
        {error}
        <GeneratedLink madeLink={submitted} link={"localhost:3000/TakeSurvey/" + customURL} shortlink={"TakeSurvey/" + customURL}/>

    </div>);

}

export default SurveyCreator;