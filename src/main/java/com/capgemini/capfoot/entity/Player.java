package com.capgemini.capfoot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String cin;
	@Column(unique = true)
	private String phone;
	@Email
	@Column(unique = true)
	private String emailAddress;
	private String password;
	private boolean isStartingPlayer;
	private boolean isCaptain;
	private boolean isAvailable;
	@ManyToOne
	private Team team;

	public Player(long id, String firstName, String emailAddress) {
		this.id = id;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
	}
}
