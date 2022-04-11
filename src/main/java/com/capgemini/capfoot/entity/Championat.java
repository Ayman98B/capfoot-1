package com.capgemini.capfoot.entity;

import java.time.LocalDate;
import java.util.List;

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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Championat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String libelle;

	// @Column(nullable = false)
	private LocalDate date_debut;

	// @Column(nullable = false)
	private LocalDate date_fin;

	@Column(columnDefinition = "varchar(255) default 'INSCRIPTION'")
	private Statut statut;

	@Column(columnDefinition = "varchar(100) default 'FALSE'")
	private boolean enCours;

	@OneToMany(mappedBy = "championat")
	private List<Groupe> groupes;

	@ManyToOne
	private Admin admin;

}
