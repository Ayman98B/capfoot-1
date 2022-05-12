package com.capgemini.capfoot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDisputee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Championship_State stage = Championship_State.GROUPE;
	private LocalDate matchDate;
	@Enumerated(EnumType.STRING)
	private Site site;
	private int scoreHome;
	private int scoreAway;
	private int[] scoreMatch;
	@Enumerated(EnumType.STRING)

	private Match_State matchState = Match_State.PENDING;
	private boolean updated = false;
	@ManyToOne
	private Team teamHome;

	@ManyToOne
	private Team teamAway;

	public MatchDisputee(Championship_State stage, Site site, Team team_home, Team team_away) {
		this.stage = stage;
		this.site = site;
		this.teamHome = team_home;
		this.teamAway = team_away;
	}


	public MatchDisputee(long l, Championship_State stage, LocalDate now, Site site, int i, int i1, Match_State state, Team team_home, Team team_away) {
		this.stage = stage;
		this.site = site;
		this.matchState = state;
		this.teamHome = team_home;
		this.teamAway = team_away;
	}
}
