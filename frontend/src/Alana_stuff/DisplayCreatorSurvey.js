import {Link} from "react-router-dom";
import React from "react";

function DisplayCreatorSurvey(props) {
    return (
        <Link to={`/ViewResults/${props.link}`}>
        <div className="display-survey" >
                <div className="survey-info">
                    <div><strong>{props.data.title}</strong></div>
                </div>
                    <div>{props.data.description}</div>
        </div>
        </Link>
    );
}

export default DisplayCreatorSurvey;