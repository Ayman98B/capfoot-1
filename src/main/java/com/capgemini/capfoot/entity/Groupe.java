package com.capgemini.capfoot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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


	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "group",fetch = FetchType.EAGER)
	@ToString.Exclude
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
