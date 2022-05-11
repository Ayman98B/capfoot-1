package com.capgemini.capfoot.exception;


public class GroupNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GroupNotFoundException(Long id) {
	    super("Could not find group with id = " + id + " ! ");
	  }
}
