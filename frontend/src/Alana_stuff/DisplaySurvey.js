import {Link} from "react-router-dom";
import React from "react";

function DisplaySurvey(props) {
    return (
        <div className="display-survey">
            <Link to={`/TakeSurvey/${props.link}`}>
            {props.data.title}
            {props.data.description}
            </Link>
        </div>
    );
}

export default DisplaySurvey;