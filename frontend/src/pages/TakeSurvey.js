import React from 'react'
import Survey from "../survey_display/Survey";


function TakeSurvey() {
    let url = window.location.pathname;

    let uniqueID = url.split("/")[2];
    console.log(uniqueID);

    return (
        <div>
            <Survey uniqueID={uniqueID}/>
        </div>
    )
}


export default TakeSurvey;

