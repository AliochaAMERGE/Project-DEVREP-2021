<template>
  <section>
    <navigation></navigation>
    <h5 class="center-align"></h5>
    <section id="firebaseui-auth-container"></section>
  </section>
</template>

<script>
// import navigation from "@/views/Navbar.vue";
import firebase from "firebase/compat/app";
import * as firebaseui from "firebaseui";
import "firebaseui/dist/firebaseui.css";
// import firebase from "firebase";
// import {OAuthProvider} from "firebase/auth"

export default {
  name: "Login",
  data() {
    return {};
  },
  // components: {
  //   navigation,
  // },
  mounted() {
    let ui = firebaseui.auth.AuthUI.getInstance();
    if (!ui) {
      ui = new firebaseui.auth.AuthUI(firebase.auth());
    }

    var uiConfig = {
      signInSuccessUrl: "/", // This redirect can be achived by route using callback.
      signInFlow: "popup",
      signInOptions: [
        firebase.auth.GoogleAuthProvider.PROVIDER_ID,
        firebase.auth.GithubAuthProvider.PROVIDER_ID,
        firebase.auth.EmailAuthProvider.PROVIDER_ID,
        firebase.auth.FacebookAuthProvider.PROVIDER_ID,
        firebase.auth.TwitterAuthProvider.PROVIDER_ID,
        firebase.auth.PhoneAuthProvider.PROVIDER_ID,
        "microsoft.com",
        "apple.com",
        "yahoo.com",
      ],
    };
    ui.start("#firebaseui-auth-container", uiConfig);
  },
};
</script>

<style>
</style>