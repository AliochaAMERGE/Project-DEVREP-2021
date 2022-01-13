<template>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bank Web Application</title>
    <link rel="apple-touch-icon" href="./../static/apple-touch-icon.png" />
    <!-- Place favicon.ico in the root directory -->
    <!--<link href="bulma.min.css" /> -->
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css"
    />
    <link rel="stylesheet" href="./../static/styles/main.css" />

    <!-- Choose the icon between :
        - "./styles/banks/banque_postale.css"
        - "./styles/banks/cic.css"
        - "./styles/banks/societe_generale.css"

        ./styles/banks/banque_postale.css ./styles/banks/cic.css ./styles/banks/societe_generale.css
      -->
    <link rel="stylesheet" href="./../static/styles/banks/banque_postale.css" />
  </head>

  <body class="container" style="margin-top: 5%">
    <!-- Columns for login functions -->
    <div v-show="false" id="sign-in-status"></div>
    <div v-show="false" id="sign-in"></div>
    <pre v-show="false" id="account-details"></pre>

    <!-- The name and description -->
    <section class="section">
      <div class="container">
        <h1 class="title primary-color-text" id="bankNameContainer">
          {{  bankName }}
        </h1>
        <h2 class="subtitle secondary-color-text" id="bankDescriptionContainer">
          {{ bankDescription }}
        </h2>
      </div>
    </section>

    <div v-show="!!user" class="columns is-mobile">
      <!-- Account displaying -->
      <div class="column is-4 is-offset-2">
        <div class="box">
          <article class="media">
            <div class="media-left">
              <figure class="image is-64x64">
                <!-- Choose the icon between :
                  - "./images/bank_icons/banque_postale.png"
                  - "./images/bank_icons/cic.png"
                  - "./images/bank_icons/societe_generale.png"
                  ./images/bank_icons/banque_postale.png ./images/bank_icons/cic.png ./images/bank_icons/societe_generale.png
                -->
                <img
                  src="./../static/images/bank_icons/banque_postale.png"
                  alt="Image"
                />
              </figure>
            </div>
            <div class="media-content">
              <div class="content">
                <p>
                  <strong
                    >Bonjour
                    <span id="userName">
                      {{ user.displayName }}
                    </span></strong
                  >
                  <br />
                  <small
                    >Current balance :
                    <span id="accountBalance">{{ accountBalance }}</span>
                    <span id="accountCurrency"> €</span></small
                  >
                </p>
              </div>
              <nav class="level is-mobile">
                <div class="level-left">
                  <a class="level-item" aria-label="info">
                    <span class="icon is-small primary-color-text">
                      <i class="fas fa-info-circle" aria-hidden="true"></i>
                    </span>
                  </a>
                </div>
              </nav>
            </div>
          </article>
        </div>
      </div>
      <div class="column is-4 is-offset-1">
        <iframe
          title="Converter"
          src="https://xeconvert.com/widget1?from=usd&to=eur&lang=&theme=blue&font=12"
          width="100%"
          height="100%"
        ></iframe>
      </div>
      <!-- Column that contains the Account box-->
    </div>

    <!-- The buttons -->
    <div v-show="!!user" class="columns">
      <div class="column" style="text-align: start">
        <!-- withdraw button -->
        <div id="WithdrawForm">
          <form>
            <label>Select an amount to withdraw </label>
            <input name="amount" v-model="withdrawAmount" />
            <a href="#" v-on:click="withdraw">Withdraw</a>
          </form>
        </div>

        <!--<button v-on:click="this.withdraw" class="button primary-color">
        <span class="icon">
          <i class="fas fa-arrow-down"></i>
        </span>
        <span>Withdraw</span>
      </button>-->
      </div>
      <div id="DepositForm">
        <form>
          <label>Select an amount to deposit </label>
          <input name="amount" v-model="depositAmount" />
          <a href="#" v-on:click="deposit">Deposit</a>
        </form>
      </div>
      <!--<div class="column" style="text-align: end">
      <button v-on:click="this.deposit" class="button primary-color">
        <span class="icon">
          <i class="fas fa-arrow-down"></i>
        </span>
        <span>deposit</span>
      </button>
    </div>-->
    </div>

    <div class="columns is-mobile">
      <div class="column is-6">
        <div class="content secondary-color-text">
          Contact
          <ul>
            <li>
              <i class="fas fa-phone"></i>
              <span id="phoneNumberContainer">{{ phone }}</span>
            </li>
            <li>
              <i class="fas fa-envelope"></i>
              <span id="mailContainer">{{ mail }}</span>
            </li>
          </ul>
        </div>
      </div>

      <div class="column is-offset-4 is-2">
        <div class="columns is-mobile">
          <div class="column is-half">
            <figure class="image is-64x64">
              <img src="./../static/images/mastercard.png" alt="Image" />
            </figure>
          </div>
          <div class="column is-half">
            <figure class="image is-64x64">
              <img
                style="margin-top: 13px"
                src="./../static/images/visa.png"
                alt="Image"
              />
            </figure>
          </div>
        </div>
      </div>
    </div>

    <div v-show="!!user" id="Logout">
      <button>
        <a href="#" v-on:click="signOut">Logout</a>
      </button>
    </div>
  </body>
</template>

<script>
// @ is an alias to /src

import axios from "axios";

import firebase from "firebase/compat/app";

export default {
  name: "Home",
  withLimitation: false,
  components: {},

  // défini les variables de notre vue
  data: function () {
    return {
      BASE_URL: `http://localhost:8082/api/`,
      phone: "0000000000",
      mail: "example@example.com",
      accountId: "-1",
      accountBalance: "100",
      overdraft: "0",
      bankName: "banque_postale",
      bankDescription: "une desc",
      connect: false,
      accountAlmostId: "0",
      depositAmount: "0",
      withdrawAmount: "0",
      user: null,
    };
  },

  mounted: function () {
    this.initWebPageWithData();
    this.initApp();
  },

  created() {
    firebase.auth().onAuthStateChanged((user) => {
      if (user) {
        this.user = user;
        this.accountId = user.uid;
        this.getAccount();
        console.log(user);
      }
    });
  },

  methods: {
    formOnSubmit: function () {
      console.log("formOnSubmit");
      this.accountId = this.accountAlmostId;
      this.getAccount();
    },

    deposit: function () {
      //const defaultAmount = 200.0;
      const args = {
        accountId: this.accountId,
        amount: this.depositAmount,
      };
      const url = this.BASE_URL + "Deposit";

      axios
        .post(url, args, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-type": "application/json",
          },
        })
        .then((account) => {
          this.fillAccountData(
            account.data.id,
            account.data.balance,
            account.data.overdraft
          );
        })
        .catch((error) => {
          console.log(error);
        });
    },

    withdraw: function () {
      if (
        confirm(
          "Do you really want to withdraw " +
            this.withdrawAmount +
            " € from your account ?"
        )
      ) {
        console.log("Withdraw");
        //const defaultAmount = 200;
        const args = {
          accountId: this.accountId,
          amount: this.withdrawAmount,
        };

        console.log(this.accountId);

        const url = this.BASE_URL + "Withdraw/";

        if (this.accountBalance - args.amount < this.overdraft) {
          // Not enough money on your account
          console.log("not Enough money TODO");
        } else {
          axios
            .post(url, args, {
              headers: {
                "Access-Control-Allow-Origin": "*",
                "Content-type": "application/json",
              },
            })
            .then((account) => {
              this.fillAccountData(
                account.data.id,
                account.data.balance,
                account.data.overdraft
              );
            })
            .catch((error) => {
              console.log(error);
            });
        }
      }
    },

    getAccount() {
      const url = this.BASE_URL + "client";
      const args = {
        name: this.user.displayName,
        email: this.user.email,
      };
      console.log("getAccount" + url);
      axios
        .post(url, args, {
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-type": "application/json",
          },
        })
        .then((account) => {
          this.fillAccountData(
            account.data.id,
            account.data.balance,
            account.data.overdraft
          );
        })
        .catch((error) => {
          console.log(error);
        });
    },

    fillAccountData(accountId, accountBalance, overdraft) {
      console.log("fillAccountData");
      this.accountId = accountId;
      this.accountBalance = accountBalance;
      this.overdraft = overdraft;
      console.log("accountId = " + this.accountId);
    },

    limitationChanged(checkbox) {
      this.withLimitation = checkbox.checked;
    },

    initWebPageWithData() {
      let donnee = {
        name: "La Banque Postale",
        description:
          "La Banque postale est une banque publique française née le 1ᵉʳ janvier 2006, filiale à 100 % du groupe La Poste, dont elle a repris les services financiers. Son réseau de distribution s'appuie sur des bureaux de poste répartis sur le territoire, dont les agents opèrent au nom, et pour le compte de la banque.",
        phoneNumber: "09 69 39 99 98",
        mail: "laposte@laposte.net",
      };
      this.populateWebpageWithData(
        donnee.name,
        donnee.description,
        donnee.phoneNumber,
        donnee.mail
      );
    },

    populateWebpageWithData(bankName, bankDescription, phone, mail) {
      this.bankName = bankName;
      this.bankDescription = bankDescription;
      this.phone = phone;
      this.mail = mail;
    },
    // Firebase

    initApp: function () {
      firebase.auth().onAuthStateChanged(
        function (user) {
          if (user) {
            // User is signed in.
            var displayName = user.displayName;
            var email = user.email;
            var emailVerified = user.emailVerified;
            var photoURL = user.photoURL;
            var uid = user.uid;
            var phoneNumber = user.phoneNumber;
            var providerData = user.providerData;
            user.getIdToken().then(function (accessToken) {
              document.getElementById("sign-in-status").textContent =
                "Signed in";
              document.getElementById("sign-in").textContent = "Sign out";
              document.getElementById("account-details").textContent =
                JSON.stringify(
                  {
                    displayName: displayName,
                    email: email,
                    emailVerified: emailVerified,
                    phoneNumber: phoneNumber,
                    photoURL: photoURL,
                    uid: uid,
                    accessToken: accessToken,
                    providerData: providerData,
                  },
                  null,
                  "  "
                );
            });
          } else {
            // User is signed out.
            document.getElementById("sign-in-status").textContent =
              "Signed out";
            document.getElementById("sign-in").textContent = "Sign in";
            document.getElementById("account-details").textContent = "null";
          }
        },
        function (error) {
          console.log(error);
        }
      );
    },
    signOut(e) {
      e.stopPropagation();
      firebase.auth().signOut();
      this.$router.push({
        name: "Login",
      });
    },
  },
};
</script>
