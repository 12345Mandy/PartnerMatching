import React, { useState, useEffect } from "react";
import "../Homepage.css";
import PopUp from "../components/PopUp";
import NewSurvey from "../components/NewSurvey";

import DisplayHomeSurveys from "../Alana_stuff/DisplayHomeSurveys"


function Homepage() {
    return (
        <div>
            <NewSurvey />
            <DisplayHomeSurveys/>
        </div>
    )
}

export default Homepage