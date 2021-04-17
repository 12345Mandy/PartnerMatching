import firebase from "firebase";
import {useEffect, useState} from "react/cjs/react.production.min";
import './Results.css';
import ViewOnlySurvey from "./ViewOnlySurvey";

function UserDisplayPair(props) {
    console.log(props.partnerData);
    return (
        <div className="partner">
            <div className="partner-name">
                <strong>You've been paired with {props.partnerData.name}!</strong>
                <br/>
                <div className="partner-email">
                    Email them at {props.partnerData.email}
                </div>
            </div>

            <div className="compare-answers">
                <ViewOnlySurvey userData={props.userData} partnerData={props.partnerData} currPoll={props.currPoll}/>
            </div>
        </div>
    );
}

export default UserDisplayPair;