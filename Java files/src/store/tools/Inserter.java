package store.tools;

import java.sql.SQLException;

import store.model.Person;
import store.dal.PersonDao;

public class Inserter {
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PersonDao personsDao = PersonDao.getInstance();
		
		Person person = new Person(2563, "bruce", "chhay", "Langara", "xyz@gmail.com", "256367789");
		person = personsDao.create(person);
	
	}
}
