package com.capgemini.capfoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    public Groupe(String groupName) {
		this.name = groupName;
    }
}
