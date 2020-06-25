package store.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import store.model.Account;

public class AccountDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static AccountDao instance = null;
	protected AccountDao() {
		connectionManager = new ConnectionManager();
	}
	public static AccountDao getInstance() {
		if(instance == null) {
			instance = new AccountDao();
		}
		return instance;
	}
	
	public Account create(Account account) throws SQLException {
		String insertAccount = "INSERT INTO Account(AccountNumber,BillingAddress,"
				+ "CardDetails, AccountCreated) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAccount);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, account.getAccountNumber());
			insertStmt.setString(2, account.getBillingAddress());
			insertStmt.setString(3, account.getCardDetails());
			insertStmt.setDate(4, (Date) account.getAccountCreated());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return account;
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
	
	public Account updateCardDetails(Account account, String cardDetails) throws SQLException {
		String updateAccount = "UPDATE Account SET CardDetails=? WHERE AccountNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAccount);
			updateStmt.setString(1, cardDetails);
			updateStmt.setInt(2, account.getAccountNumber());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			account.setCardDetails(cardDetails);
			return account;
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
	
	public Account delete(Account account) throws SQLException {
		String deleteAccount = "DELETE FROM Account WHERE AccountNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAccount);
			deleteStmt.setInt(1, account.getAccountNumber());
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

	public Account getAccountFromAccountNumber(int accountNumber) throws SQLException {
		String selectAccount = "SELECT AccountNumber,BillingAddress, CardDetails, AccountCreated "
				+ "FROM Account WHERE AccountNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAccount);
			selectStmt.setInt(1, accountNumber);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultAccountNumber = results.getInt("AccountNumber");
				String BillingAddress = results.getString("BillingAddress");
				String CardDetails = results.getString("CardDetails");
				Date accountCreated = results.getDate("AccountCreated");
				Account account = new Account(resultAccountNumber, BillingAddress, CardDetails, accountCreated);
				return account;
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
	
	/**
	public List<Account> getAccountsFromAccountNumber(int AccountNumber) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		String selectAccount = "SELECT AccountNumber,BillingAddress, CardDetails, AccountCreated "
				+ "FROM Account WHERE AccountNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAccount);
			selectStmt.setInt(1, AccountNumber);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultAccountNumber = results.getInt("AccountNumber");
				String BillingAddress = results.getString("BillingAddress");
				String CardDetails = results.getString("CardDetails");
				Date accountCreated = results.getDate("AccountCreated");
				Account account = new Account(resultAccountNumber, BillingAddress, CardDetails, accountCreated);
				accounts.add(account);
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
		return accounts;
	}**/
}
