"use strict";

var DEFAULT_TOKEN = 'NO_TOKEN';
var DEFAULT_PROVIDER = 'NO_PROVIDER';
var googleLib = '../google-lib/';
var token = DEFAULT_TOKEN;
var oauthProvider = DEFAULT_PROVIDER;
var logged = false;
window.addEventListener('load', function (_) {
  return checkLoggedStatus();
});

function appearsAsLogged() {
  $('#logout-button-container').show();
  $('#logged-tag').show();
  $('#not-logged-tag').hide();
  $('#login-buttons-container').hide();
  $('#deposit-button').prop('disabled', false);
  $('#withdraw-button').prop('disabled', false);
  $('#limit-checkbox').prop('disabled', false);
}

function appearsNotLogged() {
  $('#login-buttons-container').show();
  $('#not-logged-tag').show();
  $('#logged-tag').hide();
  $('#logout-button-container').hide();
  $('#deposit-button').prop('disabled', true);
  $('#withdraw-button').prop('disabled', true);
  $('#limit-checkbox').prop('disabled', true);
}

function checkLoggedStatus() {
  var user = firebase.auth().currentUser;

  if (user !== null) {
    user.getIdToken().then(function (value) {
      token = value;
      appearsAsLogged();
    }).catch(function (_) {
      return appearsNotLogged();
    });
  } else {
    appearsNotLogged();
  }
}

$('#logout-button').click(function (_) {
  firebase.auth().signOut().finally(function () {
    token = DEFAULT_TOKEN;
    oauthProvider = DEFAULT_PROVIDER;
    appearsNotLogged();
  });
}); //////////////////////////////////////
////////////// GOOGLE ////////////////
//////////////////////////////////////

function googleLogin() {
  var provider = new firebase.auth.GoogleAuthProvider();
  var auth = firebase.auth();
  auth.useDeviceLanguage();
  auth.signInWithPopup(provider).then(onGoogleSuccessLogin).catch(onGoogleErrorLogin);
}

function onGoogleSuccessLogin(result) {
  token = result.credential.accessToken;
  oauthProvider = 'Google';
  appearsAsLogged();
}

function onGoogleErrorLogin(error) {
  console.error('Google error ', error);
  appearsNotLogged();
} //////////////////////////////////////
///////////// MICROSOFT //////////////
//////////////////////////////////////


$('#microsoft-login-button').click(function (_) {
  var provider = new firebase.auth.OAuthProvider('microsoft.com');
  var auth = firebase.auth();
  auth.useDeviceLanguage();
  auth.signInWithPopup(provider).then(onMicrosoftSuccessLogin).catch(onMicrosoftErrorLogin);
});

function onMicrosoftSuccessLogin(result) {
  console.log(result);
  token = result.credential.accessToken;
  oauthProvider = 'Microsoft';
  appearsAsLogged();
}

function onMicrosoftErrorLogin(error) {
  console.error('Microsoft error ', error);
  appearsNotLogged();
} //////////////////////////////////////
/////////////// GITHUB ///////////////
//////////////////////////////////////


$('#github-login-button').click(function (_) {
  var provider = new firebase.auth.GithubAuthProvider();
  var auth = firebase.auth();
  auth.useDeviceLanguage();
  auth.signInWithPopup(provider).then(onGithubSuccessLogin).catch(onGithubErrorLogin);
});

function onGithubSuccessLogin(result) {
  console.log(result);
  token = result.credential.accessToken;
  oauthProvider = 'Github';
  appearsAsLogged();
}

function onGithubErrorLogin(error) {
  console.error('Github error ', error);
  appearsNotLogged();
}

var googleButton = document.getElementById('google-login-button');
googleButton.onclick = googleLogin;
//# sourceMappingURL=login.js.map