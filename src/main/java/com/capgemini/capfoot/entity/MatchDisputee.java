package com.capgemini.capfoot.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDisputee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean groupePhase;
	private boolean directEliminationPhase;
	private LocalDate matchDate;
	@Enumerated(EnumType.STRING)
	private Site site;
	private int scoreHome;
	private int scoreAway;
	private int[] scoreMatch;
	@Enumerated(EnumType.STRING)
	private State matchState = State.PENDING;

	@ManyToOne
	private Team teamHome;

	@ManyToOne
	private Team teamAway;

	public MatchDisputee(boolean b, boolean b1, Site site, Team team_home, Team team_away) {
		this.groupePhase = b;
		this.directEliminationPhase = b1;
		this.site = site;
		this.teamHome = team_home;
		this.teamAway = team_away;
	}

	public MatchDisputee(long l, boolean b, boolean b1, LocalDate now, Site site, int i, int i1, State state, Team team_home, Team team_away) {
		this.groupePhase = b;
		this.directEliminationPhase = b1;
		this.site = site;
		this.matchState = state;
		this.teamHome = team_home;
		this.teamAway = team_away;
	}
}
