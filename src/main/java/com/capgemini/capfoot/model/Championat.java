package com.capgemini.capfoot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

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

	private boolean enCours;

	/*
	 * @OneToMany private Groupe groupes;
	 * 
	 * @ManyToOne private Admin admin;
	 */

	/*
	 * public boolean isEncours() { //LocalDate currentDate = if() return true; }
	 */
}
