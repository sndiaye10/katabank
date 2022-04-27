package daveo.kata.bank.katabank.entities;

public class Withdrawal extends Operation{

	public Withdrawal() {
		super();
	}
	
	public Withdrawal(String accountNumber, int amount) {
		super(accountNumber,amount);
	}
}
