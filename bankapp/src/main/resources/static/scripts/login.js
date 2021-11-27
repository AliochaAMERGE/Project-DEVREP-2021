const DEFAULT_TOKEN = 'NO_TOKEN';
const DEFAULT_PROVIDER = 'NO_PROVIDER';

const googleLib = '../google-lib/';

let token = DEFAULT_TOKEN;
let oauthProvider = DEFAULT_PROVIDER;
let logged = false;

window.addEventListener('load', (_) => checkLoggedStatus());

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
  const user = firebase.auth().currentUser;
  if (user !== null) {
    user
      .getIdToken()
      .then((value) => {
        token = value;
        appearsAsLogged();
      })
      .catch((_) => appearsNotLogged());
  } else {
    appearsNotLogged();
  }
}

$('#logout-button').click((_) => {
  firebase
    .auth()
    .signOut()
    .finally(() => {
      token = DEFAULT_TOKEN;
      oauthProvider = DEFAULT_PROVIDER;
      appearsNotLogged();
    });
});

//////////////////////////////////////
////////////// GOOGLE ////////////////
//////////////////////////////////////

function googleLogin() {
  const provider = new firebase.auth.GoogleAuthProvider();
  const auth = firebase.auth();
  auth.useDeviceLanguage();
  auth
    .signInWithPopup(provider)
    .then(onGoogleSuccessLogin)
    .catch(onGoogleErrorLogin);
}

function onGoogleSuccessLogin(result) {
  token = result.credential.accessToken;
  oauthProvider = 'Google';
  appearsAsLogged();
}

function onGoogleErrorLogin(error) {
  console.error('Google error ', error);
  appearsNotLogged();
}

//////////////////////////////////////
///////////// MICROSOFT //////////////
//////////////////////////////////////
$('#microsoft-login-button').click((_) => {
  const provider = new firebase.auth.OAuthProvider('microsoft.com');
  const auth = firebase.auth();
  auth.useDeviceLanguage();
  auth
    .signInWithPopup(provider)
    .then(onMicrosoftSuccessLogin)
    .catch(onMicrosoftErrorLogin);
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
}

//////////////////////////////////////
/////////////// GITHUB ///////////////
//////////////////////////////////////
$('#github-login-button').click((_) => {
  const provider = new firebase.auth.GithubAuthProvider();
  const auth = firebase.auth();
  auth.useDeviceLanguage();
  auth
    .signInWithPopup(provider)
    .then(onGithubSuccessLogin)
    .catch(onGithubErrorLogin);
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

const googleButton = document.getElementById('google-login-button');
googleButton.onclick = googleLogin;
