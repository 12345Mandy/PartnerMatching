import React, { useState, useEffect } from "react";
import "./Sidebar.css";
import { SidebarData } from './SidebarData'
import { Link } from 'react-router-dom'
import firebase from "firebase"
import "../pages/default_profile.png"
//https://www.youtube.com/watch?v=5R9jFHlG6ik&ab_channel=PedroTech -> css
//https://www.youtube.com/watch?v=Law7wfdg_ls&ab_channel=DevEd -> routing
//https://www.youtube.com/watch?v=CXa0f4-dWi4&ab_channel=BrianDesign -> using routing for sidebar

/* <div id="icon">{item.icon}</div>
{" "} <div id="title">{item.title}</div> */



function Sidebar(props) {
    const logo = require('../pages/default_profile.png');
    //const logo = require(props.user.photoURL);
    const storage = firebase.storage().ref()


    function getImage(image) {
    storage.child(`${image}.png`).getDownloadURL().then((url) => {
      this.state[image] = url
      this.setState(this.state)
    })
  }

    const [currentPage, setCurrentPage] = useState("/Homepage");
    return (
        <div className="Sidebar">
            <center className="profile">
                        <img src={logo} alt=""/>
                        <p>Jessica</p>
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