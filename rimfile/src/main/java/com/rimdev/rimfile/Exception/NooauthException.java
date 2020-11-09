package com.rimdev.rimfile.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;




@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class NooauthException extends RuntimeException{
	

	
	private static final long serialVersionUID = 1L;
	public NooauthException() {
	        super();
	    }
	    public NooauthException(String message, Throwable cause) {
	    	 super(message, cause);
	       
	    }
	    public NooauthException(String message) {
	        super(message);
	    }
	    public NooauthException(Throwable cause) {
	        super(cause);
	    }
}
