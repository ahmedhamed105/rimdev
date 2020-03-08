package com.rimdev.language.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public DuplicationException() {
	        super();
	    }
	    public DuplicationException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public DuplicationException(String message) {
	        super(message);
	    }
	    public DuplicationException(Throwable cause) {
	        super(cause);
	    }
	
	

}