package store.model;

public class ProductCategories {
	protected String CategoryType;

	public ProductCategories(String categoryType) {
		CategoryType = categoryType;
	}

	public String getCategoryType() {
		return CategoryType;
	}

	public void setCategoryType(String categoryType) {
		CategoryType = categoryType;
	}
	
}
