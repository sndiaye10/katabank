package daveo.kata.bank.katabank.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import daveo.kata.bank.katabank.KatabankApplication;
import daveo.kata.bank.katabank.entities.Account;
import daveo.kata.bank.katabank.entities.DepositTransfer;
import daveo.kata.bank.katabank.entities.Withdrawal;

@SpringBootTest(classes = KatabankApplication.class)
public class AccountServiceTest {
	
	//Declare a logger to print value in the console
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//Inject service to be test
	@Autowired
	private AccountService accountService;
	
	@Test
	public void findAccountByNumber_NotAccountPresent() {
		Optional<Account> accountOptional = Optional.ofNullable(accountService.findOneAccount("ACCT256K"));
		//Very good because Optional provide us to check if the account exist or not
		//accountOptional is an object that will not content a course
		logger.info("Course is present -> {}", accountOptional.isPresent());
		assertFalse(accountOptional.isPresent());
		
		//The following test will not work
		//assertTrue(accountOptional.isPresent());
	}
	
	@Test
	void findAccountByNumber() {
		logger.info("Retrieving account is running");
		Account account = accountService.findOneAccount("ACCT0002");
		assertNotNull(account);
		
		//Or we can test by comparing with the good balance in my list accounts
		assertEquals(400, account.getBalance());
		
		//This test will not work because the right value of balance is 400
		//assertEquals(500, account.getBalance());
	}
	
	@Test
	@DirtiesContext
	void deposit() {
		logger.info("Depositing amount is running");
		//Get the account
		Account account = accountService.findOneAccount("ACCT0002");
		//Do deposit operation
		accountService.deposit(new DepositTransfer("ACCT0002", 200));
		//Get new state of this same acount
		Account accountAfterDeposit = accountService.findOneAccount("ACCT0002");
		//Do the test
		assertEquals(500, accountAfterDeposit.getBalance());
	}
	
	@Test
	@DirtiesContext
	void withdrawal() {
		logger.info("Withdrawing amount is running");
		//Get the account
		Account account = accountService.findOneAccount("ACCT0002");
		//Do deposit operation
		accountService.withdraw(new Withdrawal("ACCT0002", 100));
		//Get new state of this same acount
		Account accountAfterWithdraw = accountService.findOneAccount("ACCT0002");
		//Do the test
		assertEquals(300, accountAfterWithdraw.getBalance());
	}

}
