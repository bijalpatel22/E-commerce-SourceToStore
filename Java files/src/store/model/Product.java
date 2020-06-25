package store.model;

public class Product {
	protected String ProductId;
	protected String ProductName;
	protected int Quantity;
	protected int Price;
	protected String category;
	protected ProductCategories CategoryType;
	
	public Product(String productId, String productName, int quantity, int price, ProductCategories categoryType) {
		this.ProductId = productId;
		this.ProductName = productName;
		this.Quantity = quantity;
		this.Price = price;
		this.CategoryType = categoryType;
	}
	
	public Product(String productId, String productName, int quantity, int price, String categoryType) {
		this.ProductId = productId;
		this.ProductName = productName;
		this.Quantity = quantity;
		this.Price = price;
		this.category = categoryType;
	}

	public String getProductId() {
		return ProductId;
	}

	public void setProductId(String productId) {
		ProductId = productId;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public ProductCategories getCategoryType() {
		return CategoryType;
	}

	public void setCategoryType(ProductCategories categoryType) {
		CategoryType = categoryType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
