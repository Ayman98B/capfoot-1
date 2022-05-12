package com.capgemini.capfoot.controller;

import com.capgemini.capfoot.dto.GroupTeamResponseDto;
import com.capgemini.capfoot.dto.GroupeResponseDto;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.entity.Team;
import com.capgemini.capfoot.service.GroupService;
import com.capgemini.capfoot.service.GroupTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/groupes/dto")
@CrossOrigin("*")
public class GroupeControllerDto {

    @Autowired
    GroupService groupeService;

    @Autowired
    GroupTeamService groupTeamService;

    @PostMapping("/supprimer")
    public ResponseEntity<String> deleteGroupe(@PathVariable Long id){
        groupeService.delete(id);
        return ResponseEntity.ok("done!");
    }

    @PostMapping("/modifier")
    public ResponseEntity<String> updateGroupe(@RequestBody Groupe groupe, @PathVariable Long id){
        groupeService.update(groupe,id);
        return ResponseEntity.ok("done!");
    }
    
    @GetMapping("/getall")
    public ResponseEntity<List<GroupeResponseDto>> findAll(){
    	List<GroupeResponseDto> groupResponseDtos = new ArrayList<GroupeResponseDto>();
		for (Groupe group : groupeService.findAll()) {
			groupResponseDtos.add(GroupeResponseDto.createGroupeDto(group));
		}
		return ResponseEntity.ok(groupResponseDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Groupe>> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(groupeService.findById(id));
    }
    @GetMapping("/groupsTeams")
    public List<GroupTeamResponseDto> findAllGroupsTeams(){
        return groupTeamService.getAll();
    }

    @GetMapping("/qualifiedTeams")
    public List<Team> findQualifiedTeams(){
        return groupTeamService.qualifiedTeamsToLastSexteen();
    }

    @GetMapping("/quarterFinalsTeams")
    public void quarterFinalsTeams(){
         groupTeamService.planningQuarterFinalsMatchs();
    }

    @GetMapping("/semiFinalsTeams")
    public void semiFinalsTeams(){
        groupTeamService.planningSemiFinalsMatchs();
    }

    @GetMapping("/FinalsTeams")
    public void FinalsTeams(){
        groupTeamService.planningFinalsMatchs();
    }
}
    
    /* @GetMapping("/{id}")
    public Optional<GroupeResponseDto> findById(@PathVariable("id") Long id){
        return GroupeResponseDto.createGroupeDto(groupeService.findById(id)));
    }
    */

