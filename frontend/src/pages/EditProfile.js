import React from 'react'
import ProfilePic from "../components/ProfilePic";
import {Link} from "react-router-dom";
import "./editProfile.css"

function EditProfile(props) {
    let user = props.user
    return (
        <div>
            <div className="profile" >
                <ProfilePic user={user} className={"profilePicLarge"}/>
                <br/>
                <b>Edit Profile Image:  </b>
               <input type="file" id="myFile" name="filename"/>
                <br/><br/>

                <p><h1>Name: {user.displayName}</h1></p>
                <button>Edit Name</button>
                <br/><br/>


            </div>
        </div>
    )
}

export default EditProfile