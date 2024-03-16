package com.ecomm.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class PaymentInformation {

	@Column(name="cardholder_name")
	private String cardholderName;
	
	@Column(name="card_Number")
	private String cardNumber;
	
	@Column(name="expirationDate")
	private LocalDateTime expirationDate;
	
	@Column(name="cvv")
	private String cvv;

	public PaymentInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentInformation(String cardholderName, String cardNumber, LocalDateTime expirationDate, String cvv) {
		super();
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
	
	
}
