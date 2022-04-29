package com.capgemini.capfoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class Team {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String site;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Player> players;
    private int nbPlayers;
    @OneToMany(mappedBy ="team")
    @JsonIgnore
    private List<GroupTeam> groupTeam;

    public Team(long id, String name, String site, List<Player> players) {
        this.id=id;
        this.name=name;
        this.players=players;
    }

    public Team(String teamName, String site) {
        this.name = teamName ;
        this.site = site;
    }

    public Team(long id, String name, String site) {
        this.id = id;
        this.name = name;
        this.site= site;

    }
}
