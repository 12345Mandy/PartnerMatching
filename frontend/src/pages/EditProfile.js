import React, { useState, useEffect } from "react";
import ProfilePic from "../components/ProfilePic";
import {Link} from "react-router-dom";
import "./editProfile.css"
import firebase from "firebase";
import { Redirect } from 'react-router-dom'
import Auth from "firebase";

function EditProfile(props) {
    //let user = props.user
    const [deleteAccount, setDeleteAccount] = useState(false);
    let user = Auth.auth().currentUser
    const db = firebase.firestore();

    const removeUserAndData = () => {
        // //remove user data
        // db.collection("users").document(user.uid).delete();
        // user.delete().then(r => console.log(r));
        user.delete().then(function() {
            console.log("user successfully deleted")
        }).catch(function(error) {
            console.log("user was not successfully deleted")
        });
        setDeleteAccount(true);
    }


    return (
        <div>
            <button onClick={() => removeUserAndData()}>Delete  Account</button>
            {deleteAccount ? <Redirect to="/Login" /> : null}
            <div className="profile" >
                <ProfilePic user={user} className={"profilePicLarge"}/>
                <br/>
                <b>Edit Profile Image:  </b>
               <input type="file" id="myFile" name="filename"/>
                <br/><br/>

               <h1>Name: {user.displayName}</h1>
                <button>Edit Name</button>
                <br/><br/>


            </div>
        </div>
    )
}

export default EditProfile