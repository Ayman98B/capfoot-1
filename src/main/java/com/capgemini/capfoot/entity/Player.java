package com.capgemini.capfoot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fullName;
	@Column(unique=true)
	private String cin;
	@Column(unique=true)
	private String phone;
	@Column(unique=true)
	private String emailAddress;
	private String password;
	private boolean isStartingPlayer;
	private boolean isCaptain;
	private boolean isAvailable;
	@ManyToOne
	@ToString.Exclude
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Team team;

    public Player(long id, String fullName, String emailAddress) {
		this.id=id;
		this.fullName=fullName;
		this.emailAddress=emailAddress;
    }

	public Player(String fullName, String cin, String phone, String emailAddress, String password, boolean isStartingPlayer, boolean isCaptain, boolean isAvailable, Team team) {
		this.fullName = fullName;
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
