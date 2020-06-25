package store.dal;

import store.model.Account;
import store.model.Person;
import store.model.Users;
import store.model.Users.AccountState;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object (DAO) class to interact with the underlying Users table in your MySQL
 * instance. This is used to store {@link Users} into your MySQL instance and retrieve 
 * {@link Users} from MySQL instance.
 */
public class UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	/**
	 * Save the Users instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(UserId,UPassword,AccountState,Id,AccountNumber) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setInt(1, user.getUserId());
			insertStmt.setString(2, user.getUPassword());
			insertStmt.setString(3, user.getAccountState().name());
			insertStmt.setInt(4, user.getUserId());
			insertStmt.setInt(5, user.getAccountNumber());
		
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
			return user;
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
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
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
	public Users getUserFromId(int userid) throws SQLException {
		String selectUser = "SELECT UserId,UPassword,AccountState,Id,AccountNumber FROM User WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userid);
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
				int resultId = results.getInt("UserId");
				String upassword = results.getString("UPassword");
				Users.AccountState accountState = Users.AccountState.valueOf(results.getString("AccountState"));
				int id = results.getInt("Id");
				int accountNumber = results.getInt("AccountNumber");
				
				Person person = personDao.getPersonFromId(id);
				Account account = accountDao.getAccountFromAccountNumber(accountNumber);
				Users user = new Users(resultId, upassword, accountState, person, account);
				return user;
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
	
	public Users checkLogin(int accountnumber, String password) throws SQLException,
    ClassNotFoundException {
		
		String login = "SELECT * FROM Users WHERE  AccountNumber = ? and UPassword = ?";
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
				
				int resultId = results.getInt("UserId");
				accountnumber = results.getInt("AccountNumber");
				String upassword = results.getString("UPassword");
				//Account account = accountDao.getAccountFromAccountNumber(accountnumber);
				Users user = new Users(resultId, accountnumber, upassword);
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
	public List<Users> getUsersFromUserId(String userid) throws SQLException {
		List<Users> user = new ArrayList<Users>();
		String selectUser =
			"SELECT UserId,UPassword,AccountState,Id,AccountNumber FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, userid);
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
