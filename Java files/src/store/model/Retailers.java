package store.model;

public class Retailers extends Person{
	protected int RetailerId;
	protected String RPassword;
	protected AccountState State;
	protected Person person;
	protected Account account;
	protected int AccountNumber;
	
	public enum AccountState {
		New, Active, Banned, Blocked;
	}
	
	public Retailers(int RetailerId, String RPassword, AccountState State, Person person, Account account) {
		super(RetailerId);
		this.RPassword = RPassword;
		this.State = State;
		this.person = person;
		this.account = account;
	}
	
	public Retailers(int id, String password, AccountState new1, int accountNumber) {
		super(id);
		this.RPassword = password;
		this.State = new1;
		this.AccountNumber = accountNumber;
	}

	public Retailers(int resultId, int accountnumber2, String upassword) {
		// TODO Auto-generated constructor stub
		super(resultId);
		this.RetailerId = resultId;
		this.AccountNumber = accountnumber2;
		this.RPassword = upassword;
		
	}

	public int getRetailerId() {
		return RetailerId;
	}

	public void setRetailerId(int RetailerId) {
		this.RetailerId = RetailerId;
	}

	public String getRPassword() {
		return RPassword;
	}

	public void setRPassword(String RPassword) {
		this.RPassword = RPassword;
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
}
