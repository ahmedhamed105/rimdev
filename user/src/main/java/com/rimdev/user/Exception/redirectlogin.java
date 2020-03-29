package com.rimdev.user.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class redirectlogin extends RuntimeException{
	private static final long serialVersionUID = 1L;
	public redirectlogin() {
	        super();
	    }
	    public redirectlogin(String message, Throwable cause) {
	        super(message, cause);
	    }
	    public redirectlogin(String message) {
	        super(message);
	    }
	    public redirectlogin(Throwable cause) {
	        super(cause);
	    }
}
