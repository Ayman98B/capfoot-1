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
	private boolean groupePhase;
	private boolean directEliminationPhase;
	private LocalDate matchDate;
	private String site;
	private int scoreHome;
	private int scoreAway;
	private int[] scoreMatch;

	@ManyToOne
	private Team teamHome;

	@ManyToOne
	private Team teamAway;

    public MatchDisputee(boolean b, boolean b1, String site, Team team_home, Team team_away) {
		this.groupePhase = b;
		this.directEliminationPhase = b1;
		this.site = site;
		this.teamHome = team_home;
		this.teamAway = team_away;
    }
}
