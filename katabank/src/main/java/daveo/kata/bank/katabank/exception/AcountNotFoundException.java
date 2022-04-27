package daveo.kata.bank.katabank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AcountNotFoundException extends RuntimeException {

	public AcountNotFoundException(String message) {
		super(message);
	}

}
