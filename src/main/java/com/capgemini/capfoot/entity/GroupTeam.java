package com.capgemini.capfoot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupTeam {
	@Id
	private Long id;
	private int nbNullMatch;
	private int nbWonMatch;
	private int nbLossMatch;
	private int cumulPoint;

}