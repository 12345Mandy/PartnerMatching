import React, {useState, useEffect} from "react";
import TextBox from '../components/TextBox';
import PopUp from '../components/PopUp';
import PopUpText from "../components/PopUpText";

const StartPage = (props) => {
    const [visibility, setVisiblity] = useState("password")
    const [popUpSeen, setPopUpSeen] = useState(false);

    // referenced https://www.w3schools.com/howto/howto_js_toggle_password.asp

    function toggleVisibility() {
        if (visibility === "password") {
            setVisiblity("text");
        } else {
            setVisiblity("password");
        }
    }

    const [agreed, setAgreed] = useState(false)
    const [agreedError, setAgreedError] = useState('')

    // decompose props
    const {
        email,
        setEmail,
        password,
        setPassword,
        handleLogin,
        handleSignup,
        hasAccount,
        setHasAccount,
        emailError,
        passwordError,

        userName,
        setUserName,
    } = props;


    return (
        <section className="login">
            <div className="loginContainer">
                {hasAccount ?
                    (<h1>Sign In</h1>) :
                    (<>
                        <h1>Sign Up</h1>
                        <hr/>
                        <br></br>
                        <TextBox type={"text"} label={"Name"} focus={true} value={userName} change={setUserName}/>
                    </>)
                }
                <br></br>
                <TextBox type={"text"} label={"Username/Email"} focus={hasAccount ? true : false} value={email}
                         change={setEmail}/>
                <p className="errorMsg">{emailError}</p>
                <TextBox type={visibility} label={"Password"} focus={false} value={password} change={setPassword}/>
                <p className="errorMsg">{passwordError}</p>
                <br/>
                <div className="small">
                    Show Password: <input type="checkbox" id="showPassword" onClick={() => toggleVisibility()}/>
                </div>
                {hasAccount ?
                    (<div className="btnContainer">
                        <>
                            <button onClick={handleLogin}>Sign in</button>
                            <p>
                                Don't have an account ?
                                <span onClick={() => setHasAccount(!hasAccount)}>Sign up</span>
                            </p>
                        </>
                    </div>) :
                    (
                        <>
                            <div className="agreePrivacyContainer">
                                I accept the
                                <span onClick={() => setPopUpSeen(true)} className="hoverChange" id="fakeSpace"><u> Privacy Policy:  </u> </span>
                                <input type="checkbox" id="agreePrivacy" onClick={() => setAgreed(!agreed)}/>
                                {popUpSeen === true ? <PopUp toggle={setPopUpSeen} content={PopUpText()}/> : null}
                            </div>


                            <div className="btnContainer">
                                <button
                                    onClick={() => agreed ? handleSignup() : setAgreedError("Agree to the privacy policy to create an account")}>Sign
                                    up
                                </button>
                                <p>
                                    Have an account ?
                                    <span onClick={() => setHasAccount(!hasAccount)}>Sign in</span>
                                </p>
                            </div>
                            <p className="errorMsg">{agreed ? "" : agreedError}</p>
                        </>
                    )
                }
            </div>
        </section>
    )
}

export default StartPage;