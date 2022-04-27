package daveo.kata.bank.katabank.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import daveo.kata.bank.katabank.entities.Account;
import daveo.kata.bank.katabank.entities.DepositTransfer;
import daveo.kata.bank.katabank.entities.Operation;
import daveo.kata.bank.katabank.entities.Withdrawal;

@Component
public class AccountService {

	// Ici je vais simuler directement un objet static pour éviter
	// de générer des tables dans la base H2.
	// Mais l'idée serait de le faire

	private static List<Account> accounts = new ArrayList<Account>();
	
	private static List<Operation> operations = new ArrayList<Operation>();

	static {
		accounts.add(new Account("ACCT0001", 500));
		accounts.add(new Account("ACCT0002", 400));
		accounts.add(new Account("ACCT0003", 200));
		accounts.add(new Account("ACCT0004", 1500));
	}

	// findAllAccounts()
	public List<Account> findAllAccounts() {
		return accounts;
	}

	// findOneAccount(String number)
	public Account findOneAccount(String number) {
		return accounts.stream()
				.filter(account -> number.equals(account.getNumber()))
				.findAny()
				.orElse(null);
	}

	// saveAccount()
	
	
	// deposit(String accountNumber, int amount)
	public void deposit(DepositTransfer depositTransfer) {
		Account relatedAccount = findOneAccount(depositTransfer.getAccountNumber());
		int newBalance = relatedAccount.getBalance();
		newBalance += depositTransfer.getAmount();
		
		operations.add(new Operation(depositTransfer.getAccountNumber(), 
							depositTransfer.getAmount(), newBalance, new Date()));
		
		//Set the new account balance
		relatedAccount.setBalance(newBalance);
	}

	// withdraw(int amount)
	public void withdraw(Withdrawal withdrawal) {
		Account relatedAccount = findOneAccount(withdrawal.getAccountNumber());
		int newBalance = relatedAccount.getBalance();
		newBalance -= withdrawal.getAmount();
		
		operations.add(new Operation(withdrawal.getAccountNumber(), (withdrawal.getAmount() * -1), 
											newBalance, new Date()));
		
		//Set the new account balance
				relatedAccount.setBalance(newBalance);
	}
	
	// Find all oerations in our bank in all accounts
		public List<Operation> findAllOperations() {
			return operations;
		}
		
	//this method will return all operations in a specific account
		public List<Operation> findAccountOperations(String accountNumber){
			return operations.stream()
					.filter(operation -> accountNumber.equals(operation.getAccountNumber()))
					.collect(Collectors.toList());
		}
}
