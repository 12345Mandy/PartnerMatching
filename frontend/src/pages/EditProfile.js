import React, { useState, useEffect } from "react";
import ProfilePic from "../components/ProfilePic";
import {Link} from "react-router-dom";
import "./editProfile.css"
import "../App.css"
import firebase from "firebase";
import { Redirect } from 'react-router-dom'
import Auth from "firebase";
import TextBox from "../components/TextBox";

function EditProfile(props) {
    //let user = props.user
    const [deleteAccount, setDeleteAccount] = useState(false);
    const [newEmail, setNewEmail] = useState("")
    const [newName, setNewName] = useState("")
    const [newPassword, setNewPassword] = useState("")
    const [newPicURL, setNewPicURL] = useState(null)
    // const [emailError, setEmailError] = useState('')
    // const [logMessage, setLogMessage] = useState('')
    const [logMessage, setLogMessage] = useState('')
//https://www.youtube.com/watch?v=31MVIwvstzs&ab_channel=SoftAuthor -> change profile

    let user = Auth.auth().currentUser
    const db = firebase.firestore();

    const clearInputs = () => {
        setNewName('');
        setNewEmail('');
        setNewPassword('');
    }


    const clearErrors = () => {
        setLogMessage('');
        setLogMessage('');
    }

    const removeUserAndData = () => {
        // //remove user data
        db.collection("users").document(user.uid).delete();
        // remove account
        user.delete().then(function() {
            console.log("user successfully deleted")
        }).catch(function(error) {
            console.log("user was not successfully deleted"+      error)
        });
        setDeleteAccount(true);
    }

    const updateProfile = () => {
        updateEmail();
        updatePassword();
        user.updateProfile({
            displayName: newName !== "" ? newName: user.displayName,
            photoURL: newPicURL,
        }).then(function() {
            console.log("user successfully updated to " + newName)
            setLogMessage(logMessage  + "user info successfully updated")
            // newPassword ? user.updatePassword(newPassword) : null;
            // newEmail ? user.updateEmail(newEmail) : null;
        }).catch(function(error) {
            console.log("user not successfully updated"+  error)
        });
        clearInputs();
    }
    // useEffect(() => {
    //     if (newEmail !== "")
    //         user.updateEmail(newEmail)
    //             .then(r =>  {
    //                 console.log(r + ":user successfully updated Email")
    //                 setLogMessage(logMessage + "<br>" + "user successfully updated Email " + newEmail)
    //             })
    //             .catch(err => {
    //                 switch(err.code) {
    //                     case "auth/email-already-in-use":
    //                     case "auth/invalid-email":
    //                         setLogMessage(err.message);
    //                         break;
    //                 }
    //             });
    //     }, [newEmail]);

    function updatePassword() {
        if (newPassword !== "")
            user.updatePassword(newPassword)
                .then(r =>  console.log(r + ":user successfully updated password"))
                .catch(err => {
                    switch(err.code) {
                        case "auth/weak-password":
                            setLogMessage(err.message);
                            break;
                    }
                });
    }
    function updateEmail() {
        if (newEmail !== "")
            user.updateEmail(newEmail)
                .then(r =>  {
                    console.log(r + ":user successfully updated Email")
                    setLogMessage(logMessage + "<br>" + "user successfully updated Email " + newEmail)
                })
                .catch(err => {
                    switch(err.code) {
                        case "auth/email-already-in-use":
                        case "auth/invalid-email":
                            console.log(err.message)
                            setLogMessage(err.message);
                            break;
                    }
                });
    }


    function getNewPicUrl(e) {
        setNewPicURL(e.target.files[0])
        updateProfile();
    }

    return (
        <div className="main">
            <div className="profile" >
                <ProfilePic user={user} className={"profilePicLarge"}/>
                <br/>
                <b>Edit Profile Image:  </b>
                <input type="file" id="myFile" onChange={(e)=>getNewPicUrl(e)}/>
                <br/><br/>

                <h1>Name: {user.displayName}</h1>
                <hr/>

                <h2>Edit User Information</h2>
                <TextBox type={"text"} label={"Change name "} focus={false} value={newName} change={setNewName}/>
                <TextBox type={"text"} label={"Change email "} focus={false} value={newEmail} change={setNewEmail}/>
                <TextBox type={"text"} label={"Change password "} focus={false} value={newPassword} change={setNewPassword}/>
                <button onClick={()=>updateProfile()}>Submit changes</button>
                <br/><br/>
                <p>{logMessage}</p>
                <button onClick={() => removeUserAndData()}>Delete  Account</button>
                {deleteAccount ? <Redirect to="/Login" /> : null}
            </div>
        </div>
    )
}

export default EditProfile