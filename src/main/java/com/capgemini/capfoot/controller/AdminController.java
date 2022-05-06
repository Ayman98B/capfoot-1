package com.capgemini.capfoot.controller;

import java.util.Arrays;
import java.util.List;

import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.repository.GroupRepository;
import com.capgemini.capfoot.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capgemini.capfoot.entity.Championship;
import com.capgemini.capfoot.service.ChampionshipService;
import com.capgemini.capfoot.entity.MatchDisputee;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.MatchService;
import com.capgemini.capfoot.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
        return matchService.getAllMatchs();
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

    @PutMapping("championships/update/{idChampion}")
    public void updateChampionship(@PathVariable("idChampion") Long idChampion, @RequestBody Championship championship) {
        championshipService.updateChampionship(idChampion, championship);
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
