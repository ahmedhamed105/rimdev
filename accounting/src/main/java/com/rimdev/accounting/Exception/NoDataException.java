package com.rimdev.accounting.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public NoDataException() {
	        super();
	    }
	    public NoDataException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public NoDataException(String message) {
	        super(message);
	    }
	    public NoDataException(Throwable cause) {
	        super(cause);
	    }
	
	

}
