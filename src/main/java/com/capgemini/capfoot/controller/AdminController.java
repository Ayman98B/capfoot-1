package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.MatchService;
import com.capgemini.capfoot.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {
    @Autowired
    MatchService matchService;

    @Autowired
    TeamService teamService;


    @PostMapping("match/add")
    public MatchDisputee createMatch(@RequestBody MatchDisputee matchDisputee){
        return matchService.addMatch(matchDisputee);
    }

    @PostMapping("team/add")
    public Team createTeam(@RequestBody Team createdTeam){
        return teamService.addTeem(createdTeam);
    }

    @PutMapping("match/teams/{id}")
    public void setTeams(@PathVariable(value = "id") Long id, @RequestBody MatchDisputee setTeams){
        MatchDisputee matchToUpdate = matchService.getMatchById(id);
        matchToUpdate.setTeamHome(setTeams.getTeamHome());
        matchToUpdate.setTeamAway(setTeams.getTeamAway());
        matchService.updateMatch(id, matchToUpdate);
    }

    @PutMapping("match/score/{id}")
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

    @PutMapping("match/finalscore/{id}")
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

    @GetMapping("match/{id}")
    public MatchDisputee getMatchById(@PathVariable("id") Long id){
        return matchService.getMatchById(id);

    }
}
