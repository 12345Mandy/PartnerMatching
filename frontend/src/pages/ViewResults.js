import SurveyAdmin from '../survey_display/SurveyAdmin';
import firebase from "firebase";

function ViewResults() {
    let url = window.location.pathname;

    let uniqueID = url.split("/")[2];

    return (
        <div className="surveyResults">
            <SurveyAdmin uniqueID={uniqueID}/>
        </div>
    )
}

export default ViewResults;