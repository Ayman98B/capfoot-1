package com.capgemini.capfoot.service;

import com.capgemini.capfoot.entity.Player;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.PlayerRepository;
import com.capgemini.capfoot.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    TeamService teamService = new TeamServiceImpl(teamRepository);
    @InjectMocks
    PlayerService playerService = new PlayerServiceImpl(playerRepository);

    @Test
    public void testSaveTeam() {

        Team team = new Team();
        team.setName("test name");

        List<Player> players = new ArrayList<>();
        Player player1 = new Player(null,"test","test","test","test","test",true,true,true,null);
        Player player2 = new Player(null,"test2","test2","test2","test2","test",true,true,true,null);
        Player player3 = new Player(null,"test3","test3","test3","test3","test",true,true,true,null);
        Player player4 = new Player(null,"test4","test4","test4","test4","test",true,true,true,null);
        Player player5 = new Player(null,"test5","test5","test5","test5","test",true,true,true,null);
        Player player6 = new Player(null,"test6","test6","test6","test6","test",true,true,true,null);
        Player player7 = new Player(null,"test7","test7","test7","test7","test",true,true,true,null);

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);

        team.setPlayers(players);
        when(teamRepository.save(ArgumentMatchers.any(Team.class))).thenReturn(team);
        Team createdTeam = teamService.addTeam(team);
        assertThat(createdTeam.getPlayers().size()).isEqualTo(7);
    }


}
