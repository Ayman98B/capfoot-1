package com.capgemini.capfoot.exception;


public class MatchNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MatchNotFoundException(Long id) {
	    super("Could not find match with id = " + id + " ! ");
	  }
}
