package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Product;

public class ProductDao {

	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ProductDao instance = null;
	protected ProductDao() {
		connectionManager = new ConnectionManager();
	}
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	public Product create(Product product) throws SQLException {
		String insertProduct = "INSERT INTO Product(ProductId,ProductName,"
				+ "Quantity, Price, CategoryType) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProduct);
			insertStmt.setString(1, product.getProductId());
			insertStmt.setString(2, product.getProductName());
			insertStmt.setInt(3, product.getQuantity());
			insertStmt.setInt(4, product.getPrice());
			insertStmt.setString(5, product.getCategory());
			insertStmt.executeUpdate();
			return product;
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
	
	public Product updatePrice(Product product, int price) throws SQLException {
		String updateProduct = "UPDATE Product SET Price=? WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateProduct);
			updateStmt.setInt(1, price);
			updateStmt.setString(2, product.getProductId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			product.setPrice(price);
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Product delete(Product product) throws SQLException {
		String deleteProduct = "DELETE FROM Product WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProduct);
			deleteStmt.setString(1, product.getProductId());
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
	
	public Product getProductFromProductId(String productID) throws SQLException {
		String selectProduct = "SELECT ProductId,ProductName, Quantity, Price , CategoryType"
				+ " FROM Product WHERE ProductId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProduct);
			selectStmt.setString(1, productID);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				String resultProductId = results.getString("ProductId");
				String ProductName = results.getString("ProductName");
				int Quantity = results.getInt("Quantity");
				int Price = results.getInt("Price");
				String productCategory = results.getString("CategoryType");
				Product product = new Product(resultProductId, ProductName, Quantity, Price,productCategory);
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Product> getProductFromCategory(String category) throws SQLException {
		List<Product> products = new ArrayList<Product>();
		String selectProducts =
			"SELECT ProductId,ProductName,Quantity,Price,CategoryType FROM Product WHERE CategoryType=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProducts);
			selectStmt.setString(1, category);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String productid = results.getString("ProductId");
				String productname = results.getString("ProductName");
				int quantity = results.getInt("Quantity");
				int price = results.getInt("Price");
				String categoryType = results.getString("CategoryType");
				Product product = new Product(productid,productname,quantity,price,categoryType);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return products;
	}
}
