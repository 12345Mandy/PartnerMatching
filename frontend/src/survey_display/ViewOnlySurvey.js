import Option from "./Option";
import React, {useEffect, useState} from "react";
import firebase from "firebase";
import Question from "./Question";
import {Link} from "react-router-dom";

import ViewOnlyQuestion from "./ViewOnlyQuestion";
import ResponseComparison from "./ResponseComparison";

function ViewOnlySurvey(props) {
    const [description, setDescription] = useState("almost there...");
    const [questions, setQuestions] = useState([]);

    const [error, setError] = useState("");
    const db = firebase.firestore();

    useEffect(() => {
        getSurveyInfoFromDatabase();
    }, [])

    const getSurveyInfoFromDatabase = async () => {
        let currentSurveyRef = db.collection("surveys").doc(props.currPoll);

        setQuestions((await currentSurveyRef.collection("questions").orderBy("questionnumber").get()).docs.map(d => d.data()));
    }

    return (<div>
            <div className="poll">
                <div className="surveyInfo">
                    <h1>Response Comparison</h1>
                    <p>{description}</p>
                </div>
                {questions.map((q, qid) =>
                    <ViewOnlyQuestion
                        userData={props.userData} partnerData={props.partnerData}
                        options={q.options} question={q.question} questionNumber={q.questionnumber}/>
                    )}

            </div>
        </div>
    )
}

export default ViewOnlySurvey;