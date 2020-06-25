package store.dal;

import store.model.Account;
import store.model.Person;
import store.model.Retailers;
import store.model.Users;
import store.model.Retailers.AccountState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Retailers table in your MySQL
 * instance. This is used to store {@link Retailers} into your MySQL instance and retrieve 
 * {@link Retailers} from MySQL instance.
 */
public class RetailersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RetailersDao instance = null;
	protected RetailersDao() {
		connectionManager = new ConnectionManager();
	}
	public static RetailersDao getInstance() {
		if(instance == null) {
			instance = new RetailersDao();
		}
		return instance;
	}

	/**
	 * Save the Retailers instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Retailers create(Retailers retailer) throws SQLException {
		String insertRetailer = "INSERT INTO Retailers(RetailerId,RPassword,AccountState,Id,AccountNumber) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRetailer);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, retailer.getRetailerId());
			insertStmt.setString(2, retailer.getRPassword());
			insertStmt.setString(3, retailer.getAccountState().name());
			insertStmt.setInt(4, retailer.getPerson().getId());
			insertStmt.setInt(5, retailer.getAccount().getAccountNumber());
		
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
			return retailer;
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

	/**
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Retailers delete(Retailers retailer) throws SQLException {
		String deleteRetailer = "DELETE FROM Retailers WHERE RetailerId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRetailer);
			deleteStmt.setInt(1, retailer.getRetailerId());
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

	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Retailers getRetailerFromId(int retailerid) throws SQLException {
		String selectRetailer = "SELECT RetailerId,RPassword,AccountState,Id,AccountNumber FROM Retailer WHERE RetailerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRetailer);
			selectStmt.setInt(1, retailerid);
			// Note that we call executeQuery(). This is used for a SELECT statement
			// because it returns a result set. For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
			results = selectStmt.executeQuery();
			PersonDao personDao = PersonDao.getInstance();
			AccountDao accountDao = AccountDao.getInstance();
			// You can iterate the result set (although the example below only retrieves 
			// the first record). The cursor is initially positioned before the row.
			// Furthermore, you can retrieve fields by name and by type.
			if(results.next()) {
				int resultId = results.getInt("RetailerId");
				String RPassword = results.getString("RPassword");
				Retailers.AccountState accountState = Retailers.AccountState.valueOf(results.getString("AccountState"));
				int id = results.getInt("Id");
				int accountNumber = results.getInt("AccountNumber");
				
				Person person = personDao.getPersonFromId(id);
				Account account = accountDao.getAccountFromAccountNumber(accountNumber);
				Retailers retailer = new Retailers(resultId, RPassword, accountState, person, account);
				return retailer;
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
	
	public Retailers checkLogin(int accountnumber, String password) throws SQLException,
    ClassNotFoundException {
		
		String login = "SELECT * FROM Retailers WHERE  AccountNumber = ? and RPassword = ?";
		Connection connection = null;
		PreparedStatement loginStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			loginStmt = connection.prepareStatement(login);
			loginStmt.setInt(1, accountnumber);
			loginStmt.setString(2, password);
			results = loginStmt.executeQuery();
			if(results.next()) {
				
				int resultId = results.getInt("RetailerId");
				accountnumber = results.getInt("AccountNumber");
				String upassword = results.getString("RPassword");
				//Account account = accountDao.getAccountFromAccountNumber(accountnumber);
				Retailers user = new Retailers(resultId, accountnumber, upassword);
				return user;
		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(loginStmt != null) {
				loginStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	/**
	 * Get the matching Persons records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Persons.
	 */
	/**
	public List<Retailers> getRetailersFromRetailerId(String Retailerid) throws SQLException {
		List<Retailers> Retailer = new ArrayList<Retailers>();
		String selectRetailer =
			"SELECT RetailerId,RPassword,AccountState,Id,AccountNumber FROM Retailers WHERE RetailerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRetailer);
			selectStmt.setString(1, Retailerid);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("Id");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String address = results.getString("Address");
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				Person person = new Person(id, firstName, lastName, address, email, phone);
				persons.add(person);
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
		return persons;
	}*/
}
