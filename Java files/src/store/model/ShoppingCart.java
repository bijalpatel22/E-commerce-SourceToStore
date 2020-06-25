package store.model;

import java.util.Date;

public class ShoppingCart {
	protected int CartNumber;
	protected Date created;
	protected int accountNumber;
	protected Account account;
	
	public ShoppingCart(int cartNumber, Date created, int accountNumber) {
		this.CartNumber = cartNumber;
		this.created = created;
		this.accountNumber = accountNumber;
	}

	public int getCartNumber() {
		return CartNumber;
	}

	public void setCartNumber(int cartNumber) {
		CartNumber = cartNumber;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
}
