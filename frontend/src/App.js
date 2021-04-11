import React, { useState, useEffect } from "react";
import './App.css';
import fire from "./fire";
import Nav from './components/Nav'
import Sidebar from './components/Sidebar'
import Homepage from './pages/Homepage'
import SharedWithMe from'./pages/SharedWithMe'
import {BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Survey from './Alana_stuff/Survey'
import StartPage from "./pages/StartPage";

//https://firebase.google.com/docs/auth/web/manage-users

function App() {
  const [user, setUser] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [emailError, setEmailError] = useState('')
  const [passwordError, setPasswordError] = useState('')
  const [hasAccount, setHasAccount] = useState(false)

   //const [popUpSeen, setPopUpSeen] = useState(false);
   const [userName, setUserName] = useState('')
  //const [agreed, setAgreed] = useState(false)
   

  const clearInputs = () => {
    setUserName('');
    setEmail('');
    setPassword('');
  }


  const clearErrors = () => {
    setEmailError('');
    setPasswordError('');
  }


  const handleLogin = () => {
    clearErrors();
    fire
      .auth()
      .signInWithEmailAndPassword(email, password)
      .catch(err => {
        switch(err.code) {
          case "auth/invalid-email":
          case "auth/user-disabled":
          case "auth/user-not-found":
              setEmailError(err.message);
              break;
          case "auth/wrong-password":
              setPasswordError(err.message);
              break;
        }
      });
  }


  const handleSignup = () => {
    clearErrors();
    fire
      .auth()
      .createUserWithEmailAndPassword(email, password)
      .then((userCredential) => {
        userCredential.user.updateProfile({
          displayName: userName,
          photoURL: "gs://short-demo-login.appspot.com/default_profile.png"
        })
      })
      .catch(err => {
        switch(err.code) {
          case "auth/email-already-in-use":
          case "auth/invalid-email":
              setEmailError(err.message);
              break;
          case "auth/weak-password":
              setPasswordError(err.message);
              break;
          // case agreed === true:
          //     setPasswordError("Agree to the privacy policy in order to create an account");
        }
      });
      console.log(user);
  }

  const handleLogout = () => {
    fire.auth().signOut();
  }

  const authListener = () => {
    fire.auth().onAuthStateChanged((user) => {
      if (user) {
        clearInputs();
        setUser(user);
        console.log(user)
        
      } else {
        setUser("")
      }
    });
  };

  useEffect(() => {
    authListener(); //listen for state change
  }, []);

    

  return (
    <div className="App">
      {user ? (
        <Router>
          <section className = "hero">
            <Nav handleLogout = {handleLogout}/>
            <Sidebar user={user}/>

            <Switch>
              <Route path="/Homepage" component={Homepage}/>
              <Route path="/SharedWithMe" component={SharedWithMe}/>
            </Switch>
            
          </section>
        </Router>
        
      ): (
      
         <div>    
     
      <StartPage 
        email={email}
        setEmail={setEmail}
        password={password}
        setPassword={setPassword}
        handleLogin={handleLogin}
        handleSignup={handleSignup}
        hasAccount={hasAccount}
        setHasAccount={setHasAccount}
        emailError={emailError}
        passwordError={passwordError}

        userName = {userName}
        setUserName = {setUserName}
        //setAgreed = {setAgreed}
      />
      </div>   
      )}
    </div>
  );
}

export default App;
/* {user ? (
        <Router>
          <section className = "hero">
            <Nav handleLogout = {handleLogout}/>
            <Sidebar user={user}/>

            <Switch>
              <Route path="/Homepage" component={Homepage}/>
              <Route path="/SharedWithMe" component={SharedWithMe}/>
            </Switch>
            
          </section>
        </Router>
        
      ): (
      
         <div>    
     
      <Start 
        email={email}
        setEmail={setEmail}
        password={password}
        setPassword={setPassword}
        handleLogin={handleLogin}
        handleSignup={handleSignup}
        hasAccount={hasAccount}
        setHasAccount={setHasAccount}
        emailError={emailError}
        passwordError={passwordError}

        popUpSeen = {popUpSeen}
        setPopUpSeen = {setPopUpSeen}
        userName = {userName}
        setUserName = {setUserName}
        //setAgreed = {setAgreed}
      />
      </div>   
      )} */