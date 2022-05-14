package com.capgemini.capfoot.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.capfoot.entity.Championship_State;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Match_State;
import com.capgemini.capfoot.entity.Site;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.MatchRepository;

@RunWith(SpringRunner.class)
public class MatchServiceTest {
    @MockBean
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @Test
    public void whenUpdateScore_WillReturnUpdatedScore(){

        MatchDisputee matchDisputee =

                new MatchDisputee(1L, Championship_State.GROUPE, LocalDate.now(), Site.CASABLANCA, 0, 0,Match_State.PENDING, null, null);


        given(matchRepository.findById(anyLong())).willReturn(Optional.of(matchDisputee));

        MatchDisputee updatedScore =

                new MatchDisputee(1L, Championship_State.GROUPE, LocalDate.now(), Site.CASABLANCA, 0, 3,Match_State.PENDING, null, null);



        matchService.updateMatchFinalScore(1L,updatedScore);

        assertThat(matchDisputee.getScoreAway()).isEqualTo(3);

    }

    @Test
    public void whenSetTeams_WillReturnUpdatedMatch(){

        Team team1 = new Team(1L, "team1", Site.CASABLANCA,null, null, 7, null);
        Team team2 = new Team(1L, "team1", Site.CASABLANCA,null, null, 7, null);

        MatchDisputee matchDisputee =
                new MatchDisputee(1L, Championship_State.GROUPE, LocalDate.now(), Site.CASABLANCA, 0, 0, null, null, null);

        given(matchRepository.findById(anyLong())).willReturn(Optional.of(matchDisputee));

        MatchDisputee updatedTeams =
                new MatchDisputee(1L, Championship_State.GROUPE, LocalDate.now(), Site.CASABLANCA, 0, 0, null, team1, team2);


        matchService.setTeams(1L,updatedTeams);

        assertThat(matchDisputee.getTeamAway()).isEqualTo(team1);

    }

    @Test
    public void whenUpdateScoreTeams_WillReturnUpdatedScoreTeam(){

        MatchDisputee matchDisputee =
                new MatchDisputee(1L, Championship_State.GROUPE, LocalDate.now(), Site.CASABLANCA, 0, 0, null, null, null);

        given(matchRepository.findById(anyLong())).willReturn(Optional.of(matchDisputee));

        MatchDisputee updatedScoreTeams =
                new MatchDisputee(1L, Championship_State.GROUPE, LocalDate.now(), Site.CASABLANCA, 2, 3, null, null, null);


        matchService.updateMatchFinalScore(1L,updatedScoreTeams);

        assertThat(matchDisputee.getScoreHome()).isEqualTo(2);

    }
}
