package daveo.kata.bank.katabank.exception;

import java.util.Date;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class KataBankResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) 
			throws Exception {
		
		KataBankExceptionDetails exceptionDetails = new KataBankExceptionDetails(new Date(), ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(AccountNotFoundException ex, WebRequest request) 
			throws Exception {
		
		KataBankExceptionDetails exceptionDetails = new KataBankExceptionDetails(new Date(), ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<Object>(exceptionDetails, HttpStatus.NOT_FOUND);
		
	}
	
}
