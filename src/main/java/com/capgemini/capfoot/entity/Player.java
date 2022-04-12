package com.capgemini.capfoot.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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
	@Column(unique=true)
	private String CIN;
	private String phone;
	@Column(unique=true)
	private String emailAddress;
	private String password;
	private boolean isStartingPlayer;
	private boolean isCaptain;
	private boolean isAvailable;
	@ManyToOne
	private Team team;

}
