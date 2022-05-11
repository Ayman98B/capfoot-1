package com.capgemini.capfoot.dto;

import java.time.LocalDate;

import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Match_State;
import com.capgemini.capfoot.entity.Site;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDisputeeResponseDto {

	private Long id;
	private boolean groupePhase;
	private boolean directEliminationPhase;
	private LocalDate matchDate;

	private Site site;
	private int scoreHome;
	private int scoreAway;
	private int[] scoreMatch;
	private Match_State matchState;

	private Long teamHomeId;

	private Long teamAwayId;

	public static MatchDisputeeResponseDto createMatchDisputeeDto(MatchDisputee matchDisputee) {
		return new MatchDisputeeResponseDto(
				matchDisputee.getId(), 
				matchDisputee.isGroupePhase(),
				matchDisputee.isDirectEliminationPhase(), 
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
