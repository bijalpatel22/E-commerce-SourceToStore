package store.model;

import java.util.Date;

public class Discounts {
	protected String DiscountId;
	protected int  DisountAmount;
	protected int Validity;
	protected String Description;
	protected int AccountNumber;
	protected Account account;
	protected Date Date;
	
	public Discounts(String discountId, int disountAmount, int validity, String description, int accountNumber) {
		DiscountId = discountId;
		DisountAmount = disountAmount;
		Validity = validity;
		Description = description;
		AccountNumber = accountNumber;
	}
	
	public Discounts(String discountId, int disountAmount, int validity, String description, Date date) {
		DiscountId = discountId;
		DisountAmount = disountAmount;
		Validity = validity;
		Description = description;
		Date = date;
	}

	public Discounts(String discountId, int disountAmount, int validity, String description) {
		DiscountId = discountId;
		DisountAmount = disountAmount;
		Validity = validity;
		Description = description;
	}

	public String getDiscountId() {
		return DiscountId;
	}

	public void setDiscountId(String discountId) {
		DiscountId = discountId;
	}

	public int getDisountAmount() {
		return DisountAmount;
	}

	public void setDisountAmount(int disountAmount) {
		DisountAmount = disountAmount;
	}

	public int getValidity() {
		return Validity;
	}

	public void setValidity(int validity) {
		Validity = validity;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}
	
	
}
