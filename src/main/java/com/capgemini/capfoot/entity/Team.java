package com.capgemini.capfoot.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class Team {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private String site;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> players;
    private int nbPlayers;
    @OneToMany(mappedBy ="team")
    private List<GroupTeam> groupTeam;
}
