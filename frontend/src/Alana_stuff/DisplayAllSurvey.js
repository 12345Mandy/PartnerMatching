import React, {useState, useEffect, useRef} from 'react'
import Survey from "./Survey";
import firebase from "firebase";
import Question from "./Question";
import DisplaySurvey from "./DisplaySurvey";

function DisplayAllSurvey() {
    const [allSurveys, setSurveys] = useState([])
    const db = firebase.firestore();

    // load in all the surveys.
    const loadInfo = async () => {
        let temp = []
        // Print each document
        const data = await db.collection("surveys").onSnapshot((querySnapshot) => {
            querySnapshot.forEach((doc) => {
                temp.push(doc)
            })
            setSurveys(temp)
        });
    }

    // on page load, load in the survey
    useEffect(() => {
        loadInfo();
    }, [])


        return (
            <div>
                {allSurveys.map((s) => {
                    return (<DisplaySurvey data={s.data()} link={s.id}/>);
                })}
            </div>
        )

}

export default DisplayAllSurvey;