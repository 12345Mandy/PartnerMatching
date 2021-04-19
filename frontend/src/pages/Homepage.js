import React from "react";
import "./Homepage.css";
import NewSurvey from "../components/NewSurvey";

import DisplayHomeSurveys from "../survey_display/DisplayHomeSurveys"


function Homepage() {
    return (
        <div className="HomepageContainer">
            <NewSurvey />
            <DisplayHomeSurveys/>
        </div>
    )
}

export default Homepage