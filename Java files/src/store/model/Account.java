package store.model;

import java.util.Date;

public class Account {
	protected int AccountNumber;
	protected String BillingAddress;
	protected String CardDetails;
	protected Date AccountCreated;

	public Account(int accountNumber, String billingAddress, String cardDetails, Date accountCreated) {
		this.AccountNumber = accountNumber;
		this.BillingAddress = billingAddress;
		this.CardDetails = cardDetails;
		this.AccountCreated = accountCreated;
	}
	
	public Account(int accountNumber) {
		this.AccountNumber = accountNumber;
	}

	public Account(String billingAddress, String cardDetails, Date date) {
		this.BillingAddress = billingAddress;
		this.CardDetails = cardDetails;
		this.AccountCreated = date;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getBillingAddress() {
		return BillingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		BillingAddress = billingAddress;
	}

	public String getCardDetails() {
		return CardDetails;
	}

	public void setCardDetails(String cardDetails) {
		CardDetails = cardDetails;
	}

	public Date getAccountCreated() {
		return AccountCreated;
	}

	public void setAccountCreated(Date accountCreated) {
		AccountCreated = accountCreated;
	}
	
}
