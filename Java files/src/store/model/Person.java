package store.model;

/**
 * Persons is a simple, plain old java objects (POJO).
 * 
 * Persons/PersonsDao is the superclass for Administrators/AdministratorsDao and
 * BlogUsers/BlogUsersDao. Our implementation of Persons is a concrete class. This allows 
 * us to create records in the Persons MySQL table without having the associated records
 * in the Administrators or BlogUsers MySQL tables. Alternatively, Persons could be an
 * interface or an abstract class, which would force a Persons record to be created only
 * if an Administrators or BlogUsers record is created, too.
 */
public class Person {
	protected int Id;
	protected String FirstName;
	protected String LastName;
	protected String Address;
	protected String Email;
	protected String Phone;
	
	public Person(int Id, String FirstName, String LastName, String Address, String Email, String Phone) {
		this.Id = Id;
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Address = Address;
		this.Email = Email;
		this.Phone = Phone;
	}
	
	public Person(int Id) {
		this.Id = Id;
	}
	
	public Person(String FirstName, String LastName, String Address, String Email, String Phone) {
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.Address = Address;
		this.Email = Email;
		this.Phone = Phone;
	}

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String FirstName) {
		this.FirstName = FirstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String LastName) {
		this.LastName = LastName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}
	
	
}
