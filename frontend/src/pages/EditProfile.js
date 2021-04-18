import React, {useState, useEffect} from "react";
import ProfilePic from "../components/ProfilePic";
import {Link} from "react-router-dom";
import "./editProfile.css"
import "../App.css"
import firebase from "firebase";
import {Redirect} from 'react-router-dom'
import Auth from "firebase";
import TextBox from "../components/TextBox";
import PopUp from "../components/PopUp";
import PopUpText from "../components/PopUpText";


function EditProfile() {
    //let user = props.user
    const [newEmail, setNewEmail] = useState("")
    const [newName, setNewName] = useState("")
    const [newPassword, setNewPassword] = useState("")
    const [newPicURL, setNewPicURL] = useState(null)
    const [hasPic, setHasPic] = useState(false)
    const [errorMessage, setErrorMessage] = useState('')
    const [logMessage, setLogMessage] = useState('')

    const [deleteAcc, setDeleteAcc] = useState(false);
    const [popUpSeen, setPopUpSeen] = useState(false);
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
        setErrorMessage('');
    }


    // const removeUserAndData = () => {
    //     // //remove user data
    //     // db.collection("users").document(user.uid).delete();
    //     // remove account
    //     user.delete().then(function() {
    //         console.log("user successfully deleted")
    //     }).catch(function(error) {
    //         console.log("user was not successfully deleted"+      error)
    //     });
    //     setDeleteAccount(true);
    // }

    // Create a reference to the file to delete
    const profilePicRef = firebase.storage().ref('users/' + user.uid + '/profile.jpg')
//TODO:
    //sources: https://stackoverflow.com/questions/45386065/firebase-user-photourl-to-string
    // https://www.youtube.com/watch?v=31MVIwvstzs&ab_channel=SoftAuthor
    function getNewPicUrl(e) {
        setNewPicURL(e.target.files[0]);
        setHasPic(true);
        profilePicRef.put(e.target.files[0])
            .then(function (snapshot) {
                console.log(snapshot)
                snapshot.ref.getDownloadURL()
                    .then(function (url) {  // Now I can use url
                        user.updateProfile({
                            photoURL: url       // <- URL from uploaded photo.
                        }).then(r => {
                            setLogMessage("User image updated. Refresh page to see changes")
                        });
                    })
            })
        console.log("user image updated")
        //updateProfile();
    }

// Delete the user file in storage
    function deleteProfPic() {
        profilePicRef.delete().then(() => {
            // File deleted successfully
            console.log("profile pic deleted successfully")
        }).catch((error) => {
            // Uh-oh, an error occurred!
            console.log(error)
        });

    }

// Delete User's Account
    const removeUserAndData = () => {
        if (hasPic) {
            deleteProfPic();
        }
        user.delete().catch(function (error) {
            if (error.code === 'auth/requires-recent-login') {
                window.alert('Please sign-in and try again.');
                firebase.auth().signOut();
            }
        });
    }


    const updateProfile = () => {
        clearInputs();
        clearErrors();
        user.updateProfile({
            displayName: newName !== "" ? newName : user.displayName,
        }).then(function () {
            console.log("user successfully updated");
            console.log("New User:" + user)
            setLogMessage("user successfully updated");
            // newPassword ? user.updatePassword(newPassword) : null;
            // newEmail ? user.updateEmail(newEmail) : null;
        }).catch(function (error) {
            console.log(error)
            console.log(user)
            setErrorMessage("Error occurred when updating name.")
        });
    }


    function updatePassword() {
        clearInputs();
        clearErrors();
        if (newPassword !== "")
            user.updatePassword(newPassword)
                .then(function () {
                    console.log( "user successfully updated password")
                    setLogMessage("Successfully updated password")
                    }
                )
                .catch(err => {
                    switch (err.code) {
                        case "auth/weak-password":
                            setErrorMessage(err.message);
                            break;
                    }
                });

    }

    function updateEmail() {
        clearInputs();
        clearErrors();
        if (newEmail !== "")
            user.updateEmail(newEmail)
                .then(r => {
                    console.log(r + ":user successfully updated Email")
                    setLogMessage(logMessage + "user successfully updated email to " + newEmail)
                })

                .catch(err => {
                    switch (err.code) {
                        case "auth/email-already-in-use":
                        case "auth/invalid-email":
                            console.log(err.message)
                            setErrorMessage(err.message);
                            break;
                    }
                });
    }


    return (
        <div className="main">
            <div className="profile">
                <ProfilePic user={user} className={"profilePicLarge"}/>
                <br/>
                <b>Edit Profile Image: </b>
                <input type="file" id="myFile" accept="image/jpeg" onChange={(e) => getNewPicUrl(e)}/>
                <br/><br/>

                <h1>Name: {user.displayName}</h1>
                <hr/>


                <div className="profileChangeContainer">
                    <h2 id="EditUserInfoTitle">Edit User Information</h2>
                    <br/>
                    <div className="profileChange">
                        <TextBox className="changeBox" type={"text"} label={"Change name "} focus={false}
                                 value={newName} change={setNewName}/>{" "}
                        <button onClick={() => updateProfile()} className="profileChangeButton">Submit change</button>
                    </div>
                    <div className="profileChange">
                        <TextBox className="changeBox" type={"text"} label={"Change email "} focus={false}
                                 value={newEmail} change={setNewEmail}/>{" "}
                        <button onClick={() => updateEmail()} className="profileChangeButton">Submit change</button>
                    </div>
                    <div className="profileChange">
                        <TextBox className="changeBox" type={"text"} label={"Change password "} focus={false}
                                 value={newPassword} change={setNewPassword}/>{" "}
                        <button onClick={() => updatePassword()} className="profileChangeButton">Submit change</button>
                    </div>
                    <br/>
                    <p className="errorMsg">{errorMessage}</p>
                    {logMessage}
                </div>


                <br/><br/>

                <button className="deleteButton" onClick={() => setPopUpSeen(true)}>Delete Account</button>
                {popUpSeen === true ?
                    <PopUp
                        toggle={setPopUpSeen}
                        content={
                            <button onClick={() => removeUserAndData()}>I want to delete my Account</button>
                        }
                    /> : null}

            </div>
        </div>
    )
}

export default EditProfile
