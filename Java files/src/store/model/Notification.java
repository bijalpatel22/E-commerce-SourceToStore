package store.model;

import java.util.Date;

public class Notification {

	protected int NotificationId;
	protected String Content;
	protected int Views;
	protected Date StartDate;
	protected int AccountNumber;
	protected String ProductId;
	protected Account account;
	protected Product product;
	
	public Notification(int notificationId, String content, int views, Date startDate, int accountNumber,
			String productId) {
		NotificationId = notificationId;
		Content = content;
		Views = views;
		StartDate = startDate;
		AccountNumber = accountNumber;
		ProductId = productId;
	}

	public int getNotificationId() {
		return NotificationId;
	}

	public void setNotificationId(int notificationId) {
		NotificationId = notificationId;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getViews() {
		return Views;
	}

	public void setViews(int views) {
		Views = views;
	}

	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}
	
}
