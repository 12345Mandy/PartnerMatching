import * as RiIcons from "react-icons/ri";
import {Link} from "react-router-dom";

function NewSurvey() {
    return (
        <div className="addSurveyIcon" >
            <Link to="/CreateSurvey" className="createSurveyButton">
            <RiIcons.RiAddLine className="icon" />
            </Link>
            <span className="labelIcon"><h3> Click to create new survey</h3></span>

        </div>

    )
}

export default NewSurvey