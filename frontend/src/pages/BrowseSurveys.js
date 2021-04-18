import React, {useState, useEffect} from 'react'
import DisplayAllSurvey from "../survey_display/DisplayAllSurvey";

function BrowseSurveys() {
    return (
        <div className="surveysContainer">
            <DisplayAllSurvey/>
        </div>
    )
}

export default BrowseSurveys