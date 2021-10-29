"use strict";

function initWebPageWithData() {
  var path = '../data/banque_postale.json';
  fetch(path).then(function (response) {
    return response.json();
  }).then(function (data) {
    return populateWebpageWithData(data.name, data.description, data.phoneNumber, data.mail);
  });
}

function populateWebpageWithData(bankName, bankDescription, bankPhone, bankMail) {
  $('#bankNameContainer').html(bankName);
  $('#bankDescriptionContainer').html(bankDescription);
  $('#phoneNumberContainer').html(bankPhone);
  $('#mailContainer').html(bankMail);
}
//# sourceMappingURL=data.js.map