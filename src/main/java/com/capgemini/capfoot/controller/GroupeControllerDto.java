package com.capgemini.capfoot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capfoot.dto.GroupeResponseDto;
import com.capgemini.capfoot.entity.Groupe;
import com.capgemini.capfoot.service.GroupService;

@RestController
@RequestMapping("/api/v1/groupes/dto")
public class GroupeControllerDto {

    @Autowired
    GroupService groupeService;

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
    
    /* @GetMapping("/{id}")
    public Optional<GroupeResponseDto> findById(@PathVariable("id") Long id){
        return GroupeResponseDto.createGroupeDto(groupeService.findById(id)));
    }
    */
}
