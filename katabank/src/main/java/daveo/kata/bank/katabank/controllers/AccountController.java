package daveo.kata.bank.katabank.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import daveo.kata.bank.katabank.entities.Account;
import daveo.kata.bank.katabank.entities.DepositTransfer;
import daveo.kata.bank.katabank.entities.Operation;
import daveo.kata.bank.katabank.entities.Withdrawal;
import daveo.kata.bank.katabank.exception.AcountNotFoundException;
import daveo.kata.bank.katabank.services.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	List<Operation> operations = new ArrayList<Operation>();
	
	//Retrieve all accounts
	@RequestMapping(method = RequestMethod.GET, path = "/accounts")
	public List<Account> retrieveAllAccounts(){
		return accountService.findAllAccounts();
	}
	
	//Retrieve all accounts
	@GetMapping(path = "/accounts/{number}")
	public EntityModel<Account> retrieveOneAccount(@PathVariable String number) {
		Account account = accountService.findOneAccount(number);
		
		if (account == null) 
			throw new AcountNotFoundException("number - "+ number);
		
		//HATEOAS
		//"all-accounts", SERVER_PATH + "/accounts"
		//retrieveAllAccounts
		
		EntityModel<Account> resource = EntityModel.of(account);
		
		WebMvcLinkBuilder linkToAllAccounts = 
				linkTo(methodOn(this.getClass()).retrieveAllAccounts());
		
		WebMvcLinkBuilder linkToAllOperations = 
				linkTo(methodOn(this.getClass()).findAllOperations());
		
		//Create a link to all existing account
		resource.add(linkToAllAccounts.withRel("all-accounts"));
		
		//Create a link to all operations
		resource.add(linkToAllOperations.withRel("all-operations"));
		
		//create a link to operations in this account
		WebMvcLinkBuilder linkToAccountOperations = 
				linkTo(methodOn(this.getClass()).retrieveAccountOperations(number));
		
		resource.add(linkToAccountOperations.withRel("all-account-operations"));
		
		return resource;
	}
	
	@RequestMapping(value = "/accounts/deposit", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> deposit(@RequestBody DepositTransfer depositTransfer){
		
		try {
			accountService.deposit(depositTransfer);
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/withdrawal", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> withdraw(@RequestBody Withdrawal withdrawal){
			
			try {
				accountService.withdraw(withdrawal);
			} catch (Exception e) {
				return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
	
	@RequestMapping(path = "/operations")
	public ResponseEntity<List<Operation>> findAllOperations(){
		
		try {
			operations = accountService.findAllOperations();
		} catch (Exception e) {
			return new ResponseEntity<List<Operation>>(operations,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Operation>>(operations,HttpStatus.OK);
	}
	
	@GetMapping(path = "/accounts/{accountNumber}/operations")
	public List<Operation> retrieveAccountOperations(@PathVariable String accountNumber){
		return accountService.findAccountOperations(accountNumber);
	}

}
