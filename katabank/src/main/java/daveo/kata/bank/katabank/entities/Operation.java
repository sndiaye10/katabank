package daveo.kata.bank.katabank.entities;

import java.util.Date;

public class Operation {

	private String accountNumber;
	private int amount;
	private int newBalance;
	private Date dateOperation;
	
	protected Operation() {}
	
	public Operation(String accountNumber, int amount, int newBalance, 
			Date dateOperation) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.newBalance = newBalance;
		this.dateOperation = dateOperation;
	}

	public Operation(String accountNumber, int amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(int newBalance) {
		this.newBalance = newBalance;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	@Override
	public String toString() {
		return String.format("Operation [accountNumber=%s, amount=%s, newBalance=%s, dateOperation=%s]", accountNumber,
				amount, newBalance, dateOperation);
	}
	
	
}
