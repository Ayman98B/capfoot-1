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
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@Enumerated(EnumType.STRING)
	private Site site;
	
	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Player> players;
	private int nbPlayers;
	@OneToMany(mappedBy = "team")
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
