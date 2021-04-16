import firebase from "firebase";
import {useEffect, useState} from "react/cjs/react.production.min";

function UserDisplayPair(props) {
        return (
            <div>
                <div>
                    <strong>You've been paired with {props.partnerData.userID}!</strong>
                    <br/>
                    Email them at {props.partnerData.email}
                </div>
            </div>
        );
}

export default UserDisplayPair;