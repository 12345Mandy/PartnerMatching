import firebase from "firebase"
 
 // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "AIzaSyCTLLRnCjZXoP2FqUO_B8vunGX4Y5zI7RE",
    authDomain: "short-demo-login.firebaseapp.com",
    projectId: "short-demo-login",
    storageBucket: "short-demo-login.appspot.com",
    messagingSenderId: "567636476344",
    appId: "1:567636476344:web:ef457645b3ede106f04f4d"
  };

  // Initialize Firebase
  const fire = firebase.initializeApp(firebaseConfig);

  export default fire;



// src="https://www.gstatic.com/firebasejs/8.3.2/firebase-app.js" -> why don't we need this?