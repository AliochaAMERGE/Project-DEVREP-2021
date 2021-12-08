import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as firebase from "firebase/app";

createApp(App).use(store).use(router).mount('#app')



// Import the functions you need from the SDKs you need
    // import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyC2jvDsrcorjalYzARjLlPsWOpOdJx02w8",
    authDomain: "m2-upmc-devrep.firebaseapp.com",
    projectId: "m2-upmc-devrep",
    storageBucket: "m2-upmc-devrep.appspot.com",
    messagingSenderId: "955366924965",
    appId: "1:955366924965:web:17a16dacc7b92c258d634c",
    measurementId: "${config.measurementId}"
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);
// const analytics = getAnalytics(app);