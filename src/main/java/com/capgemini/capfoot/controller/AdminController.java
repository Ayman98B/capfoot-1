package com.capgemini.capfoot.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.service.GroupService;
import com.capgemini.capfoot.service.MatchService;
import com.capgemini.capfoot.service.TeamService;

@RestController
@RequestMapping("/api/v1/admin/")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    MatchService matchService;

    @Autowired
    TeamService teamService;

    @Autowired
    ChampionshipService championshipService;

    @Autowired
    GroupService groupService;

    @Autowired
    GroupRepository groupRepository;

    @GetMapping("matchs/all")
    public List<MatchDisputee> getAllMatchs(){
        List<MatchDisputee> listMatch = matchService.getAllMatchs();
        for (MatchDisputee matchDisputee : listMatch){
            matchDisputee.getTeamAway().setPlayers(null);
            matchDisputee.getTeamHome().setPlayers(null);
            matchDisputee.getTeamAway().setGroupTeam(null);
            matchDisputee.getTeamHome().setGroupTeam(null);
        }
        return listMatch;
    }
  
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
        matchService.setTeams(id, setTeams);
    }

    @PutMapping("matchs/score/{id}")
    public void updateMatchScore(@PathVariable(value = "id") Long id, @RequestBody MatchDisputee updateTeamsScore){
        matchService.updateMatchScore(id, updateTeamsScore);

    }

    @PutMapping("matchs/finalscore/{id}")
    public void updateMatchFinalScore (@PathVariable("id") Long id, @RequestBody MatchDisputee matchDisputee){
            matchService.updateMatchFinalScore(id, matchDisputee);

    }

    @GetMapping("matchs/{id}")
    public MatchDisputee getMatchById(@PathVariable("id") Long id){
        MatchDisputee match = matchService.getMatchById(id);
        match.getTeamAway().setPlayers(null);
        match.getTeamHome().setPlayers(null);
        match.getTeamAway().setGroupTeam(null);
        match.getTeamHome().setGroupTeam(null);
        return match;

    }

    @GetMapping("championships/getall")
    public List<Championship> getAllChampionship() {

        return championshipService.getAllChampionships();
    }

    @GetMapping("championships/{id}")
    public Championship getChampionship(@PathVariable("id") long id) {

        Championship championship = championshipService.getChampionshipById(id);

        //championship.setGroups();
        return championship;
    }

    @PostMapping("championships/add")
    public void createChampionship(@RequestBody Championship championship) {
        championshipService.createChampionship(championship);

    }

    @PutMapping("championships/update")
    public void updateChampionship(@RequestBody Championship championship) throws MessagingException {
        championshipService.updateChampionship(championship);
    }


    @DeleteMapping("championships/delete/{id}")
    public void deleteChampionship(@PathVariable("id") Long id){
        championshipService.deleteChampionship(id);
    }

    @RequestMapping(value = "/admin_auth", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "Au revoir";
    }



}
