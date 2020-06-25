package store.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Account;
import store.model.ItemsBrought;

public class ItemsBroughtDao {

	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ItemsBroughtDao instance = null;
	protected ItemsBroughtDao() {
		connectionManager = new ConnectionManager();
	}
	public static ItemsBroughtDao getInstance() {
		if(instance == null) {
			instance = new ItemsBroughtDao();
		}
		return instance;
	}
	
	public ItemsBrought create(ItemsBrought item) throws SQLException {
		String insertDiscount = "INSERT INTO ItemsBrought(OrderNumber,ProductId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDiscount);
			insertStmt.setInt(1, item.getOrderNumber());
			insertStmt.setString(2, item.getProductId());
			insertStmt.executeUpdate();
			return item;
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
	
	public ItemsBrought updateProduct(ItemsBrought item, String productId, String productReplace) throws SQLException {
		String updateProduct = "UPDATE ItemsBrought SET ProductId=? WHERE OrderNumber=? AND ProductId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateProduct);
			updateStmt.setString(1, productId);
			updateStmt.setInt(2, item.getOrderNumber());
			updateStmt.setString(3, productReplace);
			updateStmt.executeUpdate();
			item.setProductId(productId);
			return item;
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
	
	public ItemsBrought delete(ItemsBrought product) throws SQLException {
		String deleteProduct = "DELETE FROM ItemsBrought WHERE OrderNumber=? AND ProductId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteProduct);
			deleteStmt.setInt(1, product.getOrderNumber());
			deleteStmt.setString(2, product.getProductId());
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
	
	public ItemsBrought getProductFromOrderNumber(int orderNumber) throws SQLException {
		String selectItem = "SELECT OrderNumber, ProductId FROM ItemsBrought WHERE AccountNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectItem);
			selectStmt.setInt(1, orderNumber);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultOrderNumber = results.getInt("AccountNumber");
				String productid = results.getString("ProductId");
				ItemsBrought item = new ItemsBrought(resultOrderNumber, productid);
				return item;
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
	
	public List<ItemsBrought> getItemsFromOrderNumber(int orderumber) throws SQLException {
		List<ItemsBrought> items = new ArrayList<ItemsBrought>();
		String selectProducts =
			"SELECT OrderNumber, ProductId FROM ItemsBrought WHERE OrderNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProducts);
			selectStmt.setInt(1, orderumber);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String productid = results.getString("ProductId");
				int ordernumber = results.getInt("OrderNumber");
				ItemsBrought item = new ItemsBrought(ordernumber,productid);
				items.add(item);
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
		return items;
	}
}
