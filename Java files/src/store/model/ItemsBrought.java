package store.model;

public class ItemsBrought {

	protected int OrderNumber;
	protected String ProductId;
	protected Orders order;
	protected Product product;
	
	public ItemsBrought(int orderNumber) {
		OrderNumber = orderNumber;
	}
	
	public ItemsBrought(int orderNumber, String productId) {
		OrderNumber = orderNumber;
		ProductId = productId;
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

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
