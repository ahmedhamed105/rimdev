package com.rimdev.gateway.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class BlockedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public BlockedException() {
	        super();
	    }
	    public BlockedException(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public BlockedException(String message) {
	        super(message);
	    }
	    public BlockedException(Throwable cause) {
	        super(cause);
	    }
}
