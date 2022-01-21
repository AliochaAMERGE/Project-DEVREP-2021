import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

// import firebase from 'firebase'
import firebase from "firebase/compat/app";

createApp(App).use(store).use(router).mount("#app");

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyC2jvDsrcorjalYzARjLlPsWOpOdJx02w8",
  authDomain: "m2-upmc-devrep.firebaseapp.com",
  projectId: "m2-upmc-devrep",
  storageBucket: "m2-upmc-devrep.appspot.com",
  messagingSenderId: "955366924965",
  appId: "1:955366924965:web:17a16dacc7b92c258d634c",
  measurementId: "${config.measurementId}",
};

// Initialize Firebase
firebase.initializeApp(firebaseConfig);

// The Vue build version to load with the `import` command
// require("./../src/static/styles/banks/banque_postale.css");
// require("./../src/static/styles/banks/societe_generale.css");
require("./../src/static/styles/main.css");
require("./../src/static/styles/banks/cic.css");
// require("./../src/static/styles/banks/cic.css");
