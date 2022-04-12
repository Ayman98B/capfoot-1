package com.capgemini.capfoot.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Championship {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String label;

	// @Column(nullable = false)
	private LocalDate startDate;

	// @Column(nullable = false)
	private LocalDate endDate;

	@Column(columnDefinition = "varchar(255) default 'INSCRIPTION'")
	private Statut statut;

	@Column(columnDefinition = "varchar(100) default 'FALSE'")
	private boolean inProgress;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "championship")
	@ToString.Exclude
	private List<Groupe> groups;

	@ManyToOne
	private Admin admin;

}
