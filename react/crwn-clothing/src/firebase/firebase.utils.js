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


export const createUserProfileDocument = async (userAuth, additionalData) => {
    if (!userAuth) return;
    const userRef = firestore.doc(`/users/${userAuth.uid}`);
    const snapShot = await userRef.get();

    if (!snapShot.exists) {
        const {displayName, email} = userAuth;
        const createdAt = new Date();

        try {
            await userRef.set({
                displayName,
                email,
                createdAt,
                ...additionalData
            });
        } catch (error) {
            console.log('Error creating user', error.message);
        }
    }

    return userRef;
};
firebase.initializeApp(config);

export const convertCollectionsSnapshotToMap = collections => {
    const transformedCollections = collections.docs.map(doc => {
        const {title, items} = doc.data();

        return {
            routeName: encodeURI(title.toLowerCase()),
            id: doc.id,
            title,
            items
        }
    });
    return transformedCollections.reduce((accumulator, collection) => {
        accumulator[collection.title.toLowerCase()] = collection;
        return accumulator;
    }, {})
};

export const getCurrentUser = () => {
    return new Promise((resolve, reject) => {
        const unSubscribe = auth.onAuthStateChanged(userAuth => {
            unSubscribe();
            resolve(userAuth)
        }, reject)
    })
};

export const auth = firebase.auth();
export const firestore = firebase.firestore();

export const addCollectionAndDocuments = async (collectionKey, objectsToAdd) => {
    const collectionRef = firestore.collection(collectionKey);
    const batch = firestore.batch();
    objectsToAdd.forEach(obj => {
        const newDocRef = collectionRef.doc();
        batch.set(newDocRef, obj);
    });
    return await batch.commit();
};


export const googleProvider = new firebase.auth.GoogleAuthProvider();
googleProvider.setCustomParameters({prompt: 'select_account'});

export default firebase;