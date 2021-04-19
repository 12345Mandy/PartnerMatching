import React, {useState, useEffect} from "react";
import './App.css';
import fire from "./fire";
import Nav from './components/Nav'
import Sidebar from './components/Sidebar'
import Homepage from './pages/Homepage'
import BrowseSurveys from './pages/BrowseSurveys'
import EditProfile from "./pages/EditProfile";
import ViewResults from './pages/ViewResults'
import {BrowserRouter as Router, Switch, Route, Redirect} from 'react-router-dom'
import StartPage from "./pages/StartPage";
import TakeSurvey from "./pages/TakeSurvey";
import SurveyCreator from "./creating_surveys/SurveyCreator";



function App() {

    const [user, setUser] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [emailError, setEmailError] = useState('')
    const [passwordError, setPasswordError] = useState('')
    const [hasAccount, setHasAccount] = useState(false)

    const [userName, setUserName] = useState('')


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
                switch (err.code) {
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
                    photoURL: null,
                })
            })
            .catch(err => {
                switch (err.code) {
                    case "auth/email-already-in-use":
                    case "auth/invalid-email":
                        setEmailError(err.message);
                        break;
                    case "auth/weak-password":
                        setPasswordError(err.message);
                        break;
                }
            });
        console.log(user);
    }

    const handleLogout = () => {
        window.location.replace("http://localhost:3000/Homepage");
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
                <section className="hero">
                    <Nav handleLogout={handleLogout} className="topNav"/>
                    <div className="page">
                        <Router>
                            <Sidebar className="sideBarLeft" user={user}/>
                            <Switch >
                                <Route path="/Homepage"  component={Homepage} />
                                <Route path="/BrowseSurveys" component={BrowseSurveys}/>
                                <Route path="/TakeSurvey" component={TakeSurvey}/>
                                <Route path="/CreateSurvey" component={SurveyCreator}/>
                                <Route path="/EditProfile" render={() => (
                                    <EditProfile user={user}/>
                                )}/>
                                <Route path="/ViewResults" component={ViewResults}/>
                            </Switch>
                        </Router>
                    </div>
                </section>

            ) : (

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

                        userName={userName}
                        setUserName={setUserName}
                    />
                </div>
            )}
        </div>
    );

}

export default App;
