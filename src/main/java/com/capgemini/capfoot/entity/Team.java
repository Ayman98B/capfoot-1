package com.capgemini.capfoot.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Team {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private String site;
    @OneToMany(mappedBy = "team")
    @ToString.Exclude
    private List<Player> players;
    private int nbPlayers;

}
