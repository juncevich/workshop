import firebase from 'firebase/app'
import 'firebase/firestore'
import 'firebase/auth'

const config = {
    apiKey: "AIzaSyATa1Er1diR-O5OJma7Z7CqBaWvTYr8j5k",
    authDomain: "crwn-db-7ce40.firebaseapp.com",
    databaseURL: "https://crwn-db-7ce40.firebaseio.com",
    projectId: "crwn-db-7ce40",
    storageBucket: "crwn-db-7ce40.appspot.com",
    messagingSenderId: "43246073398",
    appId: "1:43246073398:web:51a60b18ca91309c69ca97",
    measurementId: "G-ZEY3Y1N3P6"
};

firebase.initializeApp(config);

export const auth = firebase.auth();
export const firestore = firebase.firestore();

const provider = new firebase.auth.GoogleAuthProvider();
provider.setCustomParameters({prompt: 'select_account'});

export const signInWithGoogle = () => auth.signInWithPopup(provider);

export default firebase;