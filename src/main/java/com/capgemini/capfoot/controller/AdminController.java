package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.MatchService;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    MatchService matchService;

    @Autowired
    TeamService teamService;

    @Autowired
    ChampionshipService championshipService;

    @PostMapping("matchs/add")
    public MatchDisputee createMatch(@RequestBody MatchDisputee matchDisputee){
        return matchService.addMatch(matchDisputee);
    }

    @PostMapping("teams/add")
    public Team createTeam(@RequestBody Team createdTeam){
        return teamService.addTeam(createdTeam);
    }

    @PutMapping("matchs/teams/{id}")
    public void setTeams(@PathVariable(value = "id") Long id, @RequestBody MatchDisputee setTeams){
        MatchDisputee matchToUpdate = matchService.getMatchById(id);
        matchToUpdate.setTeamHome(setTeams.getTeamHome());
        matchToUpdate.setTeamAway(setTeams.getTeamAway());
        matchService.updateMatch(id, matchToUpdate);
    }

    @PutMapping("matchs/score/{id}")
    public void updateMatchScore(@PathVariable(value = "id") Long id, @RequestBody MatchDisputee updateTeamsScore){

        MatchDisputee matchUpdateScore = matchService.getMatchById(id);
        matchUpdateScore.setScoreAway(updateTeamsScore.getScoreAway());
        matchUpdateScore.setScoreHome(updateTeamsScore.getScoreHome());

        int[] scoreMatch = new int[2];
        scoreMatch[0] = updateTeamsScore.getScoreAway();
        scoreMatch[1] = updateTeamsScore.getScoreHome();
        matchUpdateScore.setScoreMatch(scoreMatch);
        matchService.updateMatch(id, matchUpdateScore);

    }

    @PutMapping("matchs/finalscore/{id}")
    public void updateMatchFinalScore (@PathVariable("id") Long id, @RequestBody MatchDisputee matchDisputee){
        MatchDisputee matchFinalScore = matchService.getMatchById(id);
        int[] scoreMatch = new int[2];
            scoreMatch[0] = matchDisputee.getScoreAway();
            scoreMatch[1] = matchDisputee.getScoreHome();

                matchFinalScore.setScoreAway(matchDisputee.getScoreAway());
                matchFinalScore.setScoreHome(matchDisputee.getScoreHome());
            matchFinalScore.setScoreMatch(scoreMatch);
            matchService.updateMatch(id, matchFinalScore);

    }

    @GetMapping("matchs/{id}")
    public MatchDisputee getMatchById(@PathVariable("id") Long id){
        return matchService.getMatchById(id);

    }

    @GetMapping("championships/getall")
    public List<Championship> getAllChampionship() {
        return championshipService.getAllChampionships();
    }

    @PostMapping("championships/add")
    public void createChampionship(@RequestBody Championship championship) {
        championshipService.createChampionship(championship);
    }

    @DeleteMapping("championships/delete/{id}")
    public void deleteChampionship(@PathVariable("id") Long id){
        championshipService.deleteChampionship(id);
    }

}
