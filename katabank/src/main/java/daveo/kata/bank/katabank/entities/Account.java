package daveo.kata.bank.katabank.entities;


public class Account {
	
	private String number;
	private int balance;
	
	//To make JPA Happy we have to generate the default constructor
	//When we want to generate the account object in H2 database table
	protected Account() {}

	public Account(String number, int balance) {
		super();
		this.number = number;
		this.balance = balance;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return String.format("Account [number=%s, balance=%s]", number, balance);
	}
	

}
