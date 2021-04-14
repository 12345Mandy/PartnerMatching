import SurveyAdmin from '../Alana_stuff/SurveyAdmin';


function ViewResults() {
    let url = window.location.pathname;

    let uniqueID = url.split("/")[2];
    console.log(uniqueID);

    return (
        <div>
            <SurveyAdmin uniqueID={uniqueID}/>
        </div>
    )
}

export default ViewResults