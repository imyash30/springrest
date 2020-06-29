package com.spring.boot.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class validateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;
    private final List<String> errors;
	public validateException(String message, List<String> errors) {
		super();
		this.message = message;
		this.errors = errors;
	}
	public String getMessage() {
		return message;
	}
	public List<String> getErrors() {
		return errors;
	}
	
    
    

}
