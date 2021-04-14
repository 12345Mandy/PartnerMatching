import React, { useState, useEffect } from "react";
import "./Sidebar.css";
import { SidebarData } from './SidebarData'
import { Link } from 'react-router-dom'
import firebase from "firebase"
//import "./default.png"
import logo from "./default.png"
import ProfilePic from "./ProfilePic";

//https://www.youtube.com/watch?v=5R9jFHlG6ik&ab_channel=PedroTech -> css
//https://www.youtube.com/watch?v=Law7wfdg_ls&ab_channel=DevEd -> routing
//https://www.youtube.com/watch?v=CXa0f4-dWi4&ab_channel=BrianDesign -> using routing for sidebar
//https://firebase.google.com/docs/storage/web/download-files -> taking stuff from firebasestorage
/* <div id="icon">{item.icon}</div>
{" "} <div id="title">{item.title}</div> */



function Sidebar(props) {
    const user = props.user;


    const [currentPage, setCurrentPage] = useState("/Homepage");
    return (
        <div className="Sidebar">
            <div className="profile" >
                <ProfilePic user={user} className={"profilePic"}/>
                {/*<img src={user.photoURL ? user.photoURL: getDefaultImage()} alt="ahhh"/>*/}
                {/*<ProfilePic user={user}/>*/}
                <h3>{user.displayName}</h3>
                <br/><br/>

                {/*<button className="editProfileButton" >Edit Profile</button>*/}
                <Link to="/EditProfile" className="editProfileButton">Edit Profile</Link>
            </div>

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