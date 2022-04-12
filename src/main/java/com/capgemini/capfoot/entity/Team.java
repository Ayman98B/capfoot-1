package com.capgemini.capfoot.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Team {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTeam;
    private String site;
    private int nbPlayers;

    @OneToMany(mappedBy ="team")
    private List<GroupTeam> groupTeam;
}
