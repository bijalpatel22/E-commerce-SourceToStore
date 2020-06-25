package store.model;

public class Orders {

	protected int OrderNumber;
	protected String ShippingAddress;
	public enum OrderStatus {
		Confirmed, Processing, Dispatched, Delivered;
	};
	protected int AccountNumber;
	protected int CartNumber;
	protected Account account;
	protected ShoppingCart cart;
	
	public Orders(int orderNumber, String shippingAddress, int accountNumber, int cartNumber) {
		super();
		OrderNumber = orderNumber;
		ShippingAddress = shippingAddress;
		AccountNumber = accountNumber;
		CartNumber = cartNumber;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getShippingAddress() {
		return ShippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		ShippingAddress = shippingAddress;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public int getCartNumber() {
		return CartNumber;
	}

	public void setCartNumber(int cartNumber) {
		CartNumber = cartNumber;
	}	  

	
}
