package com.example.ECommerce.exception;

public class ValidationException extends RuntimeException{
	public ValidationException (String message) {
		super(message);
	}
}
