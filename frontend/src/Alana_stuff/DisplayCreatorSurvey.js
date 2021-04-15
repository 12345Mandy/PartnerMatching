import {Link} from "react-router-dom";
import React from "react";

function DisplayCreatorSurvey(props) {
    return (
        <div className="display-survey">
            <Link to={`/ViewResults/${props.link}`}>
                {props.data.title}
                {props.data.description}
            </Link>
        </div>
    );
}

export default DisplayCreatorSurvey;