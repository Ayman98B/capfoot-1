package com.capgemini.capfoot.entity;

import javax.persistence.*;

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
	private String CIN;
	private String phone;
	private String emailAdress;
	private String password;
	private boolean isStartingPlayer;
	private boolean isCaptain;
	@ManyToOne
	private Team team;

}
