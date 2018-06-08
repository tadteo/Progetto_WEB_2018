/*
 * Cinema Universe - Reservation System
 * Copyright (C) 2018 Domenico Stefani, Ivan Martini, Matteo Tadiello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See <http://www.gnu.org/licenses/>.
 */

$(function () {

  var owner = $('#owner');
  var cardNumber = $('#cardNumber');
  var cardNumberField = $('#card-number-field');
  var CVV = $("#cvv");
  var mastercard = $("#mastercard");
  var confirmButton = $('#confirm-purchase');
  var visa = $("#visa");
  var amex = $("#amex");

  // Use the payform library to format and validate
  // the payment fields.

  cardNumber.payform('formatCardNumber');
  CVV.payform('formatCardCVC');


  cardNumber.keyup(function () {

    amex.removeClass('transparent');
    visa.removeClass('transparent');
    mastercard.removeClass('transparent');

    if ($.payform.validateCardNumber(cardNumber.val()) == false) {
      cardNumberField.addClass('has-error');
    } else {
      cardNumberField.removeClass('has-error');
      cardNumberField.addClass('has-success');
    }

    if ($.payform.parseCardType(cardNumber.val()) == 'visa') {
      mastercard.addClass('transparent');
      amex.addClass('transparent');
    } else if ($.payform.parseCardType(cardNumber.val()) == 'amex') {
      mastercard.addClass('transparent');
      visa.addClass('transparent');
    } else if ($.payform.parseCardType(cardNumber.val()) == 'mastercard') {
      amex.addClass('transparent');
      visa.addClass('transparent');
    }
  });

  confirmButton.click(function (e) {

    e.preventDefault();

    var isCardValid = $.payform.validateCardNumber(cardNumber.val());
    var isCvvValid = $.payform.validateCardCVC(CVV.val());

    if (owner.val().length < 5) {
      alert("Wrong owner name");
    } else if (!isCardValid) {
      alert("Wrong card number");
    } else if (!isCvvValid) {
      alert("Wrong CVV");
    } else {
      // Everything is correct. Add your form submission code here.
      alert("Everything is correct");
    }
  });
});
