package store.model;

import java.util.Date;

public class Payment {

	protected int TransactionId;
	protected Date PaymentMade;
	protected String CardDetails;
	protected int OrderNumber;
	protected Orders order;
	
	public Payment(int transactionId, Date paymentMade, String cardDetails, int orderNumber) {
		super();
		TransactionId = transactionId;
		PaymentMade = paymentMade;
		CardDetails = cardDetails;
		OrderNumber = orderNumber;
	}

	public int getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(int transactionId) {
		TransactionId = transactionId;
	}

	public Date getPaymentMade() {
		return PaymentMade;
	}

	public void setPaymentMade(Date paymentMade) {
		PaymentMade = paymentMade;
	}

	public String getCardDetails() {
		return CardDetails;
	}

	public void setCardDetails(String cardDetails) {
		CardDetails = cardDetails;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}
	
}
