import SurveyAdmin from '../survey_display/SurveyAdmin';
import firebase from "firebase";

function ViewResults() {
    let url = window.location.pathname;

    let uniqueID = url.split("/")[2];

    return (
        <div>
            <SurveyAdmin uniqueID={uniqueID}/>
        </div>
    )
}

export default ViewResults;