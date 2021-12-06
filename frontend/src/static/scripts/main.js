/// <reference path="../../node_modules/jquery/dist/jquery.js" />

window.addEventListener("load", (_) => init());

function init() {
  initWebPageWithData();
  getAccount();
}

/*
 - V1 : https://localhost:5001/api/account/V1/
 - V2 : https://localhost:5001/api/account/V2/

    https://localhost:5001/api/account/V1/ https://localhost:5001/api/account/V2/
*/
const DOMAIN = "https://localhost:5001";
const BASE_URL = `${DOMAIN}/api/account/V1/`;
const ICONS = {
  Euro: '<i class="fas fa-euro-sign"></i>',
  Dollar: '<i class="fas fa-dollar-sign"></i>',
};

let withLimitation = false;

function deposit() {
  const defaultAmount = 200;
  const args = { amount: defaultAmount, withLimit: true };
  const url = BASE_URL + "Deposit/";
  $.get(url, args, (account) =>
    fillAccountData(account.id, account.balance, account.currency)
  );
}

function withdraw() {
  const defaultAmount = 200;
  const args = { amount: defaultAmount };
  const url = BASE_URL + "Withdraw/";
  $.get(url, args, (account) =>
    fillAccountData(account.id, account.balance, account.currency)
  );
}

function getAccount() {
  const url = BASE_URL;
  $.get(url, (account) =>
    fillAccountData(account.id, account.balance, account.currency)
  );
}

function fillAccountData(accountId, accountBalance, accountCurrency) {
  const currencyIcon = ICONS[accountCurrency];
  $("#accountId").text(accountId);
  $("#accountBalance").text(accountBalance);
  $("#accountCurrency").html(currencyIcon);
}

function limitationChanged(checkbox) {
  withLimitation = checkbox.checked;
}
