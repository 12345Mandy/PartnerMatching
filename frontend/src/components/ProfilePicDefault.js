import React from 'react'
import firebase from "firebase";

const ProfilePicDefault = (props) => {
    const user = props.user;
    // const logo = require(user.photoURL);
    // const storage = firebase.storage().ref()

    let storage = firebase.storage();
    // Create a reference to the file we want to download
    let gsReference = storage.refFromURL('gs://short-demo-login.appspot.com/default_profile.png');

    const getDefaultImage = () => {
        gsReference.getDownloadURL()
            .then((url) => {
                // Insert url into an <img> tag to "download"
                console.log(user.photoURL ? user.photoURL: url)
                return url;
                //return <img src={url} alt=""/>;
            })
            .catch((error) => {
                // A full list of error codes is available at
                // https://firebase.google.com/docs/storage/web/handle-errors
                switch (error.code) {
                    case 'storage/object-not-found':
                        // File doesn't exist
                        break;
                    case 'storage/unauthorized':
                        // User doesn't have permission to access the object
                        break;
                    case 'storage/canceled':
                        // User canceled the upload
                        break;
                    case 'storage/unknown':
                        // Unknown error occurred, inspect the server response
                        break;
                }
            });
    }
    return (
        <img src= {require("./default.png")} alt="ahhh"/>
        // <img src={user.photoURL ? user.photoURL: getDefaultImage()} alt="ahhh"/>
    )
}

export default ProfilePicDefault