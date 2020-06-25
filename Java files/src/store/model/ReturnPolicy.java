package store.model;

public class ReturnPolicy {

	protected String PolicyNumber;
	protected int Validity;
	protected int RefundPercent;
	protected int OrderNumber;
	protected String ProductId;
	protected Product product;
	protected Orders order;
	
	public ReturnPolicy(String policyNumber, int validity, int refundPercent, int orderNumber, String productId) {
		super();
		PolicyNumber = policyNumber;
		Validity = validity;
		RefundPercent = refundPercent;
		OrderNumber = orderNumber;
		ProductId = productId;
	}

	public String getPolicyNumber() {
		return PolicyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		PolicyNumber = policyNumber;
	}

	public int getValidity() {
		return Validity;
	}

	public void setValidity(int validity) {
		Validity = validity;
	}

	public int getRefundPercent() {
		return RefundPercent;
	}

	public void setRefundPercent(int refundPercent) {
		RefundPercent = refundPercent;
	}

	public int getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}
	
	
}
