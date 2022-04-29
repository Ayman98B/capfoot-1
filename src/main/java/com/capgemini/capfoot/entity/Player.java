package com.capgemini.capfoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
	//@Column(unique=true)
	private String cin;
	//@Column(unique=true)
	private String phone;
	//@Column(unique=true)
	private String emailAddress;
	private String password;
	private boolean isStartingPlayer;
	private boolean isCaptain;
	private boolean isAvailable;
	@ManyToOne
	private Team team;

    public Player(long id, String firstName, String emailAddress) {
		this.id=id;
		this.firstName=firstName;
		this.emailAddress=emailAddress;
    }

	public Player(String firstName, String lastName, String cin, String phone, String emailAddress, String password, boolean isStartingPlayer, boolean isCaptain, boolean isAvailable, Team team) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.cin = cin;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.password = password;
		this.isStartingPlayer = isStartingPlayer;
		this.isCaptain = isCaptain;
		this.isAvailable = isAvailable;
		this.team = team;
	}
}
