package com.rimdev.accounting.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class RedirectException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public RedirectException() {
	        super();
	    }
	    public RedirectException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public RedirectException(String message) {
	        super(message);
	    }
	    public RedirectException(Throwable cause) {
	        super(cause);
	    }
}
