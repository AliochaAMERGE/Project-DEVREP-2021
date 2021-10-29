"use strict";

/// <reference path="../../node_modules/jquery/dist/jquery.js" />
window.addEventListener("load", function (_) {
  return init();
});

function init() {
  initWebPageWithData();
  getAccount();
}
/*
 - V1 : https://localhost:5001/api/account/V1/
 - V2 : https://localhost:5001/api/account/V2/

    https://localhost:5001/api/account/V1/ https://localhost:5001/api/account/V2/
*/


var DOMAIN = "https://localhost:5001";
var BASE_URL = "".concat(DOMAIN, "/api/account/V1/");
var ICONS = {
  Euro: '<i class="fas fa-euro-sign"></i>',
  Dollar: '<i class="fas fa-dollar-sign"></i>'
};
var withLimitation = false;

function deposit() {
  var defaultAmount = 200;
  var args = {
    amount: defaultAmount,
    withLimit: true
  };
  var url = BASE_URL + "Deposit/";
  $.get(url, args, function (account) {
    return fillAccountData(account.id, account.balance, account.currency);
  });
}

function withdraw() {
  var defaultAmount = 200;
  var args = {
    amount: defaultAmount
  };
  var url = BASE_URL + "Withdraw/";
  $.get(url, args, function (account) {
    return fillAccountData(account.id, account.balance, account.currency);
  });
}

function getAccount() {
  var url = BASE_URL;
  $.get(url, function (account) {
    return fillAccountData(account.id, account.balance, account.currency);
  });
}

function fillAccountData(accountId, accountBalance, accountCurrency) {
  var currencyIcon = ICONS[accountCurrency];
  $("#accountId").text(accountId);
  $("#accountBalance").text(accountBalance);
  $("#accountCurrency").html(currencyIcon);
}

function limitationChanged(checkbox) {
  withLimitation = checkbox.checked;
}
//# sourceMappingURL=main.js.map