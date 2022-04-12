package com.capgemini.capfoot.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Groupe {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private int nbTeams;

	@ManyToOne
	private Championship championship;


	@OneToMany(mappedBy = "group")
	private List<GroupTeam> groupTeam;


}
