package com.capgemini.capfoot.entity;

import java.time.temporal.TemporalAmount;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GeneratorType;
import com.capgemini.capfoot.entity.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDisputee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private boolean groupePhase;
	private boolean directEliminationPhase;
	private Date matchDate;
	private String site;

	@ManyToOne
	private Team team;
}
