package com.capgemini.capfoot.exception;


public class TeamNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(Long id) {
	    super("Could not find team with id = " + id + " ! ");
	  }
}
