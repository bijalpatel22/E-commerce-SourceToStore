package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import store.model.ProductCategories;

public class ProductCategoriesDao {
	
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ProductCategoriesDao instance = null;
	protected ProductCategoriesDao() {
		connectionManager = new ConnectionManager();
	}
	public static ProductCategoriesDao getInstance() {
		if(instance == null) {
			instance = new ProductCategoriesDao();
		}
		return instance;
	}
	
	public ProductCategories create(ProductCategories category) throws SQLException {
		String insertCategory = "INSERT INTO ProductCategories(CategoryType) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCategory);
			insertStmt.setString(1, category.getCategoryType());
			insertStmt.executeUpdate();
			return category;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public ProductCategories delete(ProductCategories category) throws SQLException {
		String deleteCategory = "DELETE FROM Productcategories WHERE AccountNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCategory);
			deleteStmt.setString(1, category.getCategoryType());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
