package com.capgemini.capfoot.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int nbTeams;

	@ManyToOne
	private Championship championship;

	@OneToMany(mappedBy = "group")
	private List<GroupTeam> groupTeams;

	public Groupe(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Groupe(String groupName, Championship championship) {

		this.name = groupName;
		this.championship = championship;
	}
}
