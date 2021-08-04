package com.example.demo.exception;

public class AccountNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2100204636917459103L;
	public static final String SUB_TYPE = "ACCOUNT_API:ACCOUNT_NOT_FOUND";

	public AccountNotFoundException(Integer id) {
	    super("Could not find account with identifier " + id);
	  }
	
}
