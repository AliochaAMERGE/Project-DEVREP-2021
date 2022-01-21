<template>
  <body class="container" style="margin-top: 5%">
    <!-- Columns for login functions -->
    <div v-show="false" id="sign-in-status"></div>
    <div v-show="false" id="sign-in"></div>
    <pre v-show="false" id="account-details"></pre>

    <!-- The name and description -->
    <section class="section">
      <div class="container">
        <h1 class="title primary-color-text" id="bankNameContainer">
          {{ bankName }}
        </h1>
        <h2 class="subtitle secondary-color-text" id="bankDescriptionContainer">
          {{ bankDescription }}
        </h2>
      </div>
    </section>

    <div v-if="user" class="columns is-mobile">
      <!-- Account displaying -->
      <div class="column is-4 is-offset-2" style="width: 50%; float: left">
        <div class="box">
          <article class="media">
            <div class="media-left">
              <figure class="image is-64x64">
                <img
                  src="./../static/images/bank_icons/cic.png"
                  width="250"
                  alt="Image"
                />
              </figure>
            </div>
            <div class="media-content">
              <div class="content">
                <p style="font-size: 25px">
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
      <div
        class="column is-4 is-offset-1"
        style="width: 50%; float: left; margin-top: 5%"
      >
        





      </div>

      <div class="column is-offset-4 is-2">
        <div class="columns is-mobile">
          








          <div class="column is-half">
            <figure class="image is-64x64">
              <img
                style="margin-top: 13px"
                src="./../static/images/visa.png"
                width="100"
                alt="Image"
              />
            </figure>
          </div>
        </div>
      </div>

      <!-- The buttons (form) (Withdraw & Deposit) -->
      <div
        v-show="user"
        class="column is-5 is-offset-1"
        style="display: inline-block"
      >
        <!-- withdraw button -->
        <div class="column" style="float: left; margin: 50px" id="WithdrawForm">
          <form>
            <label>Select an amount to withdraw </label>
            <input name="amount" v-model="withdrawAmount" size="5" />
            <button v-on:click="withdraw">Withdraw</button>
          </form>
        </div>
        <div class="column" style="float: right; margin: 50px" id="DepositForm">
          <form>
            <label>Select an amount to deposit </label>
            <input name="amount" v-model="depositAmount" size="5" />
            <button v-on:click="deposit">Deposit</button>
          </form>
        </div>
      </div>

      <!-- Logout button which send to the login page -->
      <div
        class="column is-6 is-offset-1"
        style="margin-top: 5%"
        v-show="user"
        id="Logout"
      >
        <button class="btn-logout" style="vertical-align: middle">
          <a v-on:click="signOut">
            <span>Logout</span>
          </a>
        </button>
      </div>
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
      accountBalance: "-1",
      overdraft: "-1",
      bankName: "myBankName",
      bankDescription: "myBankDescription",
      depositAmount: "-1",
      withdrawAmount: "-1",
      user: null,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "application/json",
      },
    };
  },

  mounted: function () {
    this.initWebPageWithData();
    this.initApp();
  },

  created: function () {
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
    /**
     * Deposit function
     * send a POST to BASE_URL/Deposit
     * the request contains the account id and the amount to deposit
     * then update the displayed information
     */
    deposit: function () {
      const args = {
        accountId: this.accountId,
        amount: this.depositAmount,
      };
      const url = this.BASE_URL + "Deposit";

      if (this.amount > 0) {
        axios
          .post(url, args, this.headers)
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
    },

    /**
     * Withdraw function
     * send a POST to BASE_URL/Withdraw
     * the request contains the account id and the amount to withdraw
     * ONLY IF the local balance contains enough funds (overdraft included)
     * It won't be checked again (several requests can break the limit)
     * then update the displayed information
     */
    withdraw: function () {
      if (
        confirm(
          "Do you really want to withdraw " +
            this.withdrawAmount +
            " € from your account ?"
        )
      ) {
        const args = {
          accountId: this.accountId,
          amount: this.withdrawAmount,
        };

        const url = this.BASE_URL + "Withdraw/";

        if (
          this.amount > 0 &&
          this.accountBalance - args.amount < this.overdraft
        ) {
          // Not enough money on your account
        } else {
          axios
            .post(url, args, this.headers)
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

    /**
     * Get the account data from an email,
     * If it doesn't exists, it will be created.
     * Once the account data is retrieved,
     * local data will be updated.
     */
    getAccount() {
      const url = this.BASE_URL + "client";
      const args = {
        name: this.user.displayName,
        email: this.user.email,
      };
      axios
        .post(url, args, this.headers)
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

    /**
     * Set the local account data.
     */
    fillAccountData(accountId, accountBalance, overdraft) {
      this.accountId = accountId;
      this.accountBalance = accountBalance;
      this.overdraft = overdraft;
    },

    /**
     * Init the bank information on page load
     */
    initWebPageWithData() {
      let donnee = require("./../static/data/cic.json");
      this.bankName = donnee.name;
      this.bankDescription = donnee.description;
      this.phone = donnee.phoneNumber;
      this.mail = donnee.mail;
    },

    /**
     * Firebase initialization
     * -> set the user data
     */
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

    /**
     * Firebase signout function.
     * Then return to Login view
     */
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

<style>
.btn-logout {
  display: inline-block;
  border-radius: 4px;
  background-color: #eb8f8f;
  border: none;
  color: #ffffff;
  text-align: center;
  font-size: 18px;
  padding: 20px;
  width: 120px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 5px;
}

.btn-logout:active {
  color: yellow;
  background-color: transparent;
  text-decoration: underline;
}

.btn-logout span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.btn-logout span:after {
  content: "\00bb";
  position: absolute;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.btn-logout:hover span {
  padding-right: 25px;
}

.btn-logout:hover span:after {
  opacity: 1;
  right: 0;
}
</style>


