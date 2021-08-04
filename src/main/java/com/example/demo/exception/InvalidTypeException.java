package com.example.demo.exception;

public class InvalidTypeException extends RuntimeException {
	private static final long serialVersionUID = -7905588761534752358L;
	public static final String SUB_TYPE = "ACCOUNT_API:BAD_REQUEST";

	public InvalidTypeException(String type) {
	    super("Invalid type " + type);
	}
}
