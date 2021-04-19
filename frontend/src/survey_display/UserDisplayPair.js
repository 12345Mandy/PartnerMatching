import firebase from "firebase";
import {useEffect, useState} from "react/cjs/react.production.min";
import './Results.css';
import ViewOnlySurvey from "./ViewOnlySurvey";

function UserDisplayPair(props) {
    if (props.partnerData.length === 0) {
        return (
            <div className="partner">
                <div className="partner-name">
                    <strong>You've been paired with {props.partnerData[0].name}!</strong>
                    <br/>
                    <div className="partner-email">
                        Email them at {props.partnerData[0].email}
                    </div>
                </div>

                <div className="compare-answers">
                    <ViewOnlySurvey userData={props.userData} partnerData={props.partnerData} currPoll={props.currPoll}/>
                </div>
            </div>
        );
    } else {
        return (
            <div className="partner">
                <div className="partner-name">
                    <strong>You've been paired with {props.partnerData[0].name} and {props.partnerData[1].name}!</strong>
                    <br/>
                    <div className="partner-email">
                        Email them at {props.partnerData[0].email} and {props.partnerData[1].email}
                    </div>
                </div>

                <div className="compare-answers">
                    <ViewOnlySurvey userData={props.userData} partnerData={props.partnerData} currPoll={props.currPoll}/>
                </div>
            </div>
        );
    }

}

export default UserDisplayPair;