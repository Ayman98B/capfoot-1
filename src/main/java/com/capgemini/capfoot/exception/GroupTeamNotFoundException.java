package com.capgemini.capfoot.exception;


public class GroupTeamNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GroupTeamNotFoundException(Long id) {
	    super("Could not find group_team with id = " + id + " ! ");
	  }
}
