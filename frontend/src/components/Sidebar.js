import React, {useState} from "react";
import "./Sidebar.css";
import {SidebarData} from './SidebarData'
import {Link} from 'react-router-dom'
import ProfilePic from "./ProfilePic";
import Auth from "firebase";

//New Citation
//https://www.youtube.com/watch?v=5R9jFHlG6ik&ab_channel=PedroTech -> css
//https://www.youtube.com/watch?v=Law7wfdg_ls&ab_channel=DevEd -> routing
//https://www.youtube.com/watch?v=CXa0f4-dWi4&ab_channel=BrianDesign -> using routing for sidebar
//https://firebase.google.com/docs/storage/web/download-files -> taking stuff from firebasestorage


function Sidebar() {
    let user = Auth.auth().currentUser


    const [currentPage, setCurrentPage] = useState("");
    return (
        <div className="Sidebar">
            <div className="profile">
                <ProfilePic user={user} className={"profilePic"}/>
                <br/>
                <h3>{user.displayName}</h3>
                <br/><br/>
                <div onClick={() => setCurrentPage("/EditProfile")}>
                    <Link to="/EditProfile" className="editProfileButton">Edit Profile</Link>
                </div>

            </div>

            <ul className='SidebarList'>
                {SidebarData.map((item, index) => {
                    return (
                        <li key={index} onClick={() => setCurrentPage(item.path)}
                            className={item.path === currentPage ? "rowClicked" : "row"}>
                            <Link to={item.path}>
                                <span id="icon">{item.icon}</span>
                                <span id="title">{item.title}</span>
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

