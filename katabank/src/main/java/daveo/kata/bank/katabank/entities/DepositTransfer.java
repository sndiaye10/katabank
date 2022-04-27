package daveo.kata.bank.katabank.entities;

public class DepositTransfer extends Operation {
	
	public DepositTransfer() {
		super();
	}
	
	public DepositTransfer(String accountNumber, int amount) {
		super(accountNumber,amount);
	}
}
