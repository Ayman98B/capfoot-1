package com.capgemini.capfoot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Team {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private String site;
    private int nbPlayers;

}
