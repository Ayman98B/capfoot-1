package com.capgemini.capfoot.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@Enumerated(EnumType.STRING)
	private Site site;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Player> players;
	private int nbPlayers;
	@OneToMany(mappedBy = "team")
	@ToString.Exclude
	private List<GroupTeam> groupTeam;

	public Team(long id, String name, String site, List<Player> players) {
		this.id = id;
		this.name = name;
		this.players = players;
	}

	public Team(String teamName, Site site) {
		this.name = teamName;
		this.site = site;
	}
}
