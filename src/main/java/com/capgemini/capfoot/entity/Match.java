package com.capgemini.capfoot.entity;

import java.time.temporal.TemporalAmount;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean phaseDeGroupe;
	private boolean phaseEliminationDirecte;
	private Date dateDuMatch;
	private String site;

	@ManyToOne
	private Team team;
}
