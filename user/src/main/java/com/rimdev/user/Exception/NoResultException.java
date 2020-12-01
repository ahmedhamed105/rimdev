package com.rimdev.user.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoResultException() {
		super();
	}
	
	 @Override
	    public synchronized Throwable fillInStackTrace() {
	        return this;
	    }

	public NoResultException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoResultException(String message) {
		super(message);
	}

	public NoResultException(Throwable cause) {
		super(cause);
	}

}
