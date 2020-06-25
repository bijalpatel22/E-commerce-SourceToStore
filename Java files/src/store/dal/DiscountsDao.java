package store.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import store.model.Discounts;

public class DiscountsDao {

	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static DiscountsDao instance = null;
	protected DiscountsDao() {
		connectionManager = new ConnectionManager();
	}
	public static DiscountsDao getInstance() {
		if(instance == null) {
			instance = new DiscountsDao();
		}
		return instance;
	}
	
	public Discounts create(Discounts discount) throws SQLException {
		String insertAccount = "INSERT INTO discounts(DiscountId,DisountAmount,"
				+ "Validity, Description, AccountNumber) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAccount);
			insertStmt.setString(1, discount.getDiscountId());
			insertStmt.setInt(2, discount.getDisountAmount());
			insertStmt.setInt(3, discount.getValidity());
			insertStmt.setString(4, discount.getDescription());
			insertStmt.setInt(5, discount.getAccountNumber());
			insertStmt.executeUpdate();
			return discount;
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
	
	public Discounts getDiscountFromAccountNumber(int accountNumber) throws SQLException {
		String selectAccount = "SELECT DiscountId, DisountAmount, Validity, Description, account.AccountNumber, account.AccountCreated"
				+ " FROM Account Join Discounts on account.AccountNumber = discounts.AccountNumber"
				+ " WHERE discounts.AccountNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAccount);
			selectStmt.setInt(1, accountNumber);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String discountId = results.getString("DiscountId");
				int discountAmount = results.getInt("DisountAmount");
				int validity = results.getInt("Validity");
				String description = results.getString("Description");
				Date date = results.getDate("AccountCreated");
				Discounts discount = new Discounts(discountId, discountAmount, validity, description, date);
				return discount;
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
}
