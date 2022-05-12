package com.capgemini.capfoot.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Match_State;
import com.capgemini.capfoot.entity.Site;

public class MatchCreationDto {
	
	@Enumerated(EnumType.STRING)
	private Championship_State stage = Championship_State.GROUPE;
	private LocalDate matchDate;

	private Site site;
	private int scoreHome;
	private int scoreAway;
	private int[] scoreMatch;
	private Match_State matchState;

	private Long teamHomeId;

	private Long teamAwayId;

	public static MatchResponseDto createMatchDisputeeDto(MatchDisputee matchDisputee) {
		return new MatchResponseDto(
				matchDisputee.getId(), 
				matchDisputee.getStage(),
				matchDisputee.getMatchDate(), 
				matchDisputee.getSite(),
				matchDisputee.getScoreHome(), 
				matchDisputee.getScoreAway(), 
				matchDisputee.getScoreMatch(),
				matchDisputee.getMatchState(),
				matchDisputee.getTeamHome().getId(), 
				matchDisputee.getTeamAway().getId());
	}
}
