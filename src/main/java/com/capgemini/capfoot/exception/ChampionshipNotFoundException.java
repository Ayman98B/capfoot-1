package com.capgemini.capfoot.exception;


public class ChampionshipNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ChampionshipNotFoundException(Long id) {
	    super("Could not find championship with id = " + id + " ! ");
	  }
}
