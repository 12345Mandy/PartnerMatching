import React, { useState, useEffect } from "react";
import * as RiIcons from "react-icons/ri";
import Survey from "../Alana_stuff/Survey";


function NewSurvey(props) {
    return (
        <div className="addSurveyIcon" >
            <RiIcons.RiAddLine className="icon" onClick={()=>props.setSurveyVisibility(true)}/>
            <span className="labelIcon"><h3> Click to create new survey</h3></span>
        </div>

    )
}

export default NewSurvey