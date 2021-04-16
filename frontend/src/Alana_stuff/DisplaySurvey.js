import {Link} from "react-router-dom";
import React from "react";

function DisplaySurvey(props) {
    return (
        <div className="hover-over">
        <Link to={`/TakeSurvey/${props.link}`}>
            <div className="display-survey" >
                <div className="survey-info">
                    <div><strong>{props.data.title}</strong></div>
                </div>
                <div>{props.data.description}</div>
            </div>
        </Link>
        </div>
    );
}

export default DisplaySurvey;