import React, { useState, useEffect } from "react";
import firebase from "firebase";

const ProfilePic = (props) => {
    const [imageUrl, setImageUrl] = useState(null);
    //const logo = require('../images/default_profile.png');
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
                console.log(user.photoURL ? user.photoURL : url)
                setImageUrl(url);
                //return <img src={url} alt=""/>;
            })
            .catch((error) => {
                // A full list of error codes is available at
                // https://firebase.google.com/docs/storage/web/handle-errors
                switch (error.code) {
                    case 'storage/object-not-found':
                        // File doesn't exist
                        console.log(error.code + "File doesn't exist")
                        break;
                    case 'storage/unauthorized':
                        // User doesn't have permission to access the object
                        console.log(error.code + ": User doesn't have permission to access the object")
                        break;
                    case 'storage/canceled':
                        // User canceled the upload
                        console.log(error.code + ": User canceled the upload")
                        break;
                    case 'storage/unknown':
                        // Unknown error occurred, inspect the server response\
                        console.log(error.code + ": Unknown error occurred, inspect the server response")
                        break;
                }
            });
    }

    useEffect(() => {
            if (user.photoURL) {
                setImageUrl(user.photoURL);
            } else {
                setImageUrl(getDefaultImage());
            }
        }
        , []);

    useEffect(() => {
            if (user.photoURL) {
                setImageUrl(user.photoURL);
            }
        }
        , [user.photoURL]);

    return (
        <img src={imageUrl} alt="Profile-pic could not load" className={props.className}/>
        // <img src={user.photoURL ? user.photoURL: getDefaultImage()} alt="ahhh"/>
    )
}

export default ProfilePic