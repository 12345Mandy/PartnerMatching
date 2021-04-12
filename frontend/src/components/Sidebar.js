import React, { useState, useEffect } from "react";
import "./Sidebar.css";
import { SidebarData } from './SidebarData'
import { Link } from 'react-router-dom'
import firebase from "firebase"
//import "./default.png"
import logo from "./default.png"
import ProfilePicDefault from "./ProfilePicDefault";

//https://www.youtube.com/watch?v=5R9jFHlG6ik&ab_channel=PedroTech -> css
//https://www.youtube.com/watch?v=Law7wfdg_ls&ab_channel=DevEd -> routing
//https://www.youtube.com/watch?v=CXa0f4-dWi4&ab_channel=BrianDesign -> using routing for sidebar
//https://firebase.google.com/docs/storage/web/download-files -> taking stuff from firebasestorage
/* <div id="icon">{item.icon}</div>
{" "} <div id="title">{item.title}</div> */



function Sidebar(props) {
    //const logo = require('../images/default_profile.png');
    const user = props.user;
   // // const logo = require(user.photoURL);
   // // const storage = firebase.storage().ref()
   //
   //  let storage = firebase.storage();
   //  // Create a reference to the file we want to download
   //  let gsReference = storage.refFromURL('gs://short-demo-login.appspot.com/default_profile.png');

// Get the download URL
//     const getDefaultImage = () => {
//         gsReference.getDownloadURL()
//             .then((url) => {
//                 // Insert url into an <img> tag to "download"
//                 console.log(user.photoURL ? user.photoURL: url)
//                 return url;
//                 //return <img src={url} alt=""/>;
//             })
//             .catch((error) => {
//                 // A full list of error codes is available at
//                 // https://firebase.google.com/docs/storage/web/handle-errors
//                 switch (error.code) {
//                     case 'storage/object-not-found':
//                         // File doesn't exist
//                         break;
//                     case 'storage/unauthorized':
//                         // User doesn't have permission to access the object
//                         break;
//                     case 'storage/canceled':
//                         // User canceled the upload
//                         break;
//                     case 'storage/unknown':
//                         // Unknown error occurred, inspect the server response
//                         break;
//                 }
//             });
//     }

//<img src={user.photoURL ? user.photoURL: getDefaultImage()} alt=""/>
    //<img src = {getDefaultImage()} alt=""/>
    //<img src={require('./default.png')} alt ="LOADDDD"/>
    //<img src={require(logo)} alt ="LOADDDD"/>

    const [currentPage, setCurrentPage] = useState("/Homepage");
    return (
        <div className="Sidebar">
            <center className="profile">
                {/*<img src={logo} alt ="LOADDDD"/>*/}
                {/*<img src={user.photoURL ? user.photoURL: getDefaultImage()} alt="ahhh"/>*/}
                <ProfilePicDefault user={user}/>
                        <p>{user.displayName}</p>
             </center>
             <button>Edit Profile</button>
             <br/>
             <br/>
            <ul className='SidebarList'>
                {SidebarData.map((item, index) => {
                    return (
                        <li key={index} onClick={()=>setCurrentPage(item.path)} className= {item.path === currentPage ? "rowClicked" : "row"}>
                            <Link to={item.path}>
                                <span id="icon">{item.icon}</span>
                                <span id="title">{item.title}</span>
                                {/* <div id="icon">{item.icon}</div>
                                {" "} <div id="title">{item.title}</div>    */}
                            </Link>
                        </li>
                    )
                })
                    
                }

            </ul>
        </div>
    )
}

export default Sidebar
{/* <span id="icon">{item.icon}</span>
{" "} <span id="title">{item.title}</span>  */}


// <center className="profile">
//     <img src={logo} alt=""/>
//     <p>Jessica</p>
// </center>