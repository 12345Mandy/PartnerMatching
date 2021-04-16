import React, { useState, useEffect } from "react";
import Survey from '../Alana_stuff/Survey'
import "../Homepage.css";
import PopUp from "../components/PopUp";
import NewSurvey from "../components/NewSurvey";

import DisplayHomeSurveys from "../Alana_stuff/DisplayHomeSurveys"


function Homepage() {
    const [surveyVisibility, setSurveyVisibility] = useState(false)
    return (
        <div>
            <NewSurvey setSurveyVisibility={setSurveyVisibility}/>
            {surveyVisibility ? ( <Survey />) : null}
            <DisplayHomeSurveys/>
        </div>
    )
}

export default Homepage