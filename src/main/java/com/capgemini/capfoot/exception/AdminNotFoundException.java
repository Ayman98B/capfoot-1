package com.capgemini.capfoot.exception;


public class AdminNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdminNotFoundException(Long id) {
	    super("Could not find admin with id = " + id + " ! ");
	  }
}
