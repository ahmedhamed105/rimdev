package com.rimdev.rimcart.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PopupException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public PopupException() {
	        super();
	    }
	    public PopupException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public PopupException(String message) {
	        super(message);
	    }
	    public PopupException(Throwable cause) {
	        super(cause);
	    }
	
	

}
