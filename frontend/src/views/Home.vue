<template>
  <head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Bank Web Application</title>
    <link rel="apple-touch-icon" href="./../static/apple-touch-icon.png"/>
    <!-- Place favicon.ico in the root directory -->
    <!--<link href="bulma.min.css" /> -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.0/css/bulma.min.css"/>
    <link rel="stylesheet" href="./../static/styles/main.css"/>

    <!-- Choose the icon between :
        - "./styles/banks/banque_postale.css"
        - "./styles/banks/cic.css"
        - "./styles/banks/societe_generale.css"

        ./styles/banks/banque_postale.css ./styles/banks/cic.css ./styles/banks/societe_generale.css
      -->
    <link rel="stylesheet" href="./../static/styles/banks/banque_postale.css"/>
  </head>


  <body class="container" style="margin-top: 5%">
  <!-- Columns for login functions -->
  <div class="columns">
    <!-- Buttons -->
    <div class="column">
      <span id="login-buttons-container">
        <button class="button is-info is-outlined" id="google-login-button" style="display: contents">
          <span class="icon">
            <i class="fab fa-google"></i>
          </span>
          <span>Google login</span>
        </button>
        <button class="button is-link is-outlined" id="microsoft-login-button">
          <span class="icon">
            <i class="fab fa-microsoft"></i>
          </span>
          <span>Microsoft login</span>
        </button>
        <button class="button is-dark is-outlined" id="github-login-button">
          <span class="icon">
            <i class="fab fa-github"></i>
          </span>
          <span>Github login</span>
        </button>
      </span>
      <span id="logout-button-container">
        <button v-if='connect' class="button is-danger is-outlined" id="logout-button">
          <span class="icon">
            <i class="fas fa-sign-out-alt"></i>
          </span>
          <span>Logout</span>
        </button>
      </span>
    </div>
    <!-- Column that contains log states tags -->
    <div class="column" style="text-align: right">
      <span v-if="connect" id="logged-tag" class="tag is-success is-medium">Connected</span>
      <span v-else id="not-logged-tag" class="tag is-warning is-medium">Not connected</span>
    </div>
  </div>

  <!-- The name and description -->
  <section class="section">
    <div class="container">
      <h1 class="title primary-color-text" id="bankNameContainer">{{bankName}}</h1>
      <h2 class="subtitle secondary-color-text" id="bankDescriptionContainer">{{bankDescription}}</h2>
    </div>
  </section>

  <div class="columns is-mobile">
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
              <img src="./../static/images/bank_icons/banque_postale.png" alt="Image"/>
            </figure>
          </div>
          <div class="media-content">
            <div class="content">
              <p>
                <strong>Account n°<span id="accountId">{{accountId}}</span></strong> <br/>
                <small>Current balance : <span id="accountBalance">{{accountBalance}}</span>
                  <span id="accountCurrency">Euros</span></small>
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
      <iframe title="Converter" src="https://xeconvert.com/widget1?from=usd&to=eur&lang=&theme=blue&font=12"
              width="100%" height="100%"></iframe>
    </div>
    <!-- Column that contains the Account box-->
  </div>

  <!-- The buttons -->
  <div class="columns">
    <div class="column" style="text-align: start">
      <button id="withdraw-button" onclick="this.withdraw()" class="button primary-color">
        <span class="icon">
          <i class="fas fa-arrow-down"></i>
        </span>
        <span>Withdraw</span>
      </button>
      <!--<label class="checkbox">
          <input id="limit-checkbox" type="checkbox" onchange="limitationChanged(this)">
          With limitation
        </label>-->
    </div>
    <div class="column" style="text-align: end">
      <button id="deposit-button" onclick="this.deposit()" class="button secondary-color">
        <span class="icon">
          <i class="fas fa-arrow-up"></i>
        </span>
        <span>Deposit</span>
      </button>
    </div>
  </div>

  <div class="columns is-mobile">
    <div class="column is-6">
      <div class="content secondary-color-text">
        Contact
        <ul>
          <li>
            <i class="fas fa-phone"></i>
            <span id="phoneNumberContainer">{{phone}}</span>
          </li>
          <li>
            <i class="fas fa-envelope"></i>
            <span id="mailContainer">{{mail}}</span>
          </li>
        </ul>
      </div>
    </div>

    <div class="column is-offset-4 is-2">
      <div class="columns is-mobile">
        <div class="column is-half">
          <figure class="image is-64x64">
            <img src="src/static/images/mastercard.png" alt="Image"/>
          </figure>
        </div>
        <div class="column is-half">
          <figure class="image is-64x64">
            <img style="margin-top: 13px" src="./../static/images/visa.png" alt="Image"/>
          </figure>
        </div>
      </div>
    </div>
  </div>

  </body>

</template>

<script>
// @ is an alias to /src


import axios from 'axios';


export default {
  name: 'Home',
  withLimitation: false,
  components: {},

  // défini les variables de notre vue
  data: function () {
    return {
      DOMAIN : "https://localhost:8080",
      BASE_URL : this.DOMAIN + `/api/`,
      phone : '0000000000',
      mail : 'example@example.com',
      accountId : '1',
      accountBalance : '100',
      bankName : 'banque_postale',
      bankDescription : 'une desc',
      connect : false,
    }
  },

  mounted : function () {
    this.initWebPageWithData();
    // this.getAccount();
  },

  methods: {

    deposit() {
      //const defaultAmount = 200;
      //const args = {amount: defaultAmount, withLimit: true};
      const url = this.BASE_URL + "api/";
      // TODO CHANGER 1
      axios.get(url + "1").then(account => {
        this.fillAccountData(account.id, account.balance)
      })
    },

    withdraw() {
      const defaultAmount = 200;
      const args = {amount: defaultAmount};
      const url = this.BASE_URL + "Withdraw/";
      axios.get(url + "/" + args).then(account =>
          this.fillAccountData(account.id, account.balance)
      );
    },

    getAccount() {
      const url = this.BASE_URL;
      axios.get(url).then(account =>
          this.fillAccountData(account.id, account.balance)
      );
    },

    fillAccountData(accountIdTemp, accountBalance) {
      this.accountId = accountIdTemp;
      this.accountBalance = accountBalance;
    },

    limitationChanged(checkbox) {
      this.withLimitation = checkbox.checked;
    },

    initWebPageWithData() {
      let donnee = {
        "name": "La Banque Postale",
        "description": "La Banque postale est une banque publique française née le 1ᵉʳ janvier 2006, filiale à 100 % du groupe La Poste, dont elle a repris les services financiers. Son réseau de distribution s'appuie sur des bureaux de poste répartis sur le territoire, dont les agents opèrent au nom, et pour le compte de la banque.",
        "phoneNumber": "09 69 39 99 98",
        "mail": "laposte@laposte.net"
      }
      console.log("YO")
      console.log(donnee)
      this.populateWebpageWithData( donnee.name, donnee.description, donnee.phoneNumber, donnee.mail);
    },

    populateWebpageWithData(
        bankName,
        bankDescription,
        phone,
        mail
    ) {
      console.log(bankName + " " + bankDescription + " " + phone + " " + mail + " ");
      this.bankName = bankName;
      this.bankDescription = bankDescription;
      this.phone = phone;
      this.mail = mail;
    }
  }
}

</script>
