package store.model;

public class Users extends Person{
	protected int UserId;
	protected String UPassword;
	protected AccountState State;
	protected Person person;
	protected Account account;
	protected int AccountNumber;
	
	public enum AccountState {
		New, Active, Banned, Blocked;
	}
	
	public Users(int UserId, String UPassword, AccountState State, Person person, Account account) {
		super(UserId);
		this.UPassword = UPassword;
		this.State = State;
		this.person = person;
		this.account = account;
	}
	
	public Users(int userId, String password, AccountState State, int accno) {
		super(userId);
		this.UPassword = password;
		this.State = State;
		this.AccountNumber = accno;
	}
	
	public Users(int UserId, int accountnumber, String password) {
		super(UserId);
		this.AccountNumber = accountnumber;
		this.UPassword = password;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int UserId) {
		this.UserId = UserId;
	}

	public String getUPassword() {
		return UPassword;
	}

	public void setUPassword(String UPassword) {
		this.UPassword = UPassword;
	}

	public AccountState getAccountState() {
		return State;
	}

	public void setAccountState(AccountState State) {
		this.State = State;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}

	public int getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		AccountNumber = accountNumber;
	}

}
