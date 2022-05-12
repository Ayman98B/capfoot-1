package com.capgemini.capfoot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

<<<<<<< HEAD

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.*;

=======
>>>>>>> main
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

	@Column(nullable = false)
	private LocalDate startDate = LocalDate.now();

	// @Column(nullable = false)
	private LocalDate endDate = LocalDate.now().plusDays(30);

	@Enumerated(EnumType.STRING)
	private Championship_State statut = Championship_State.INSCRIPTION;

	@Column(nullable = false)
	private boolean progress = false;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "championship", fetch = FetchType.EAGER)
	@ToString.Exclude
	private List<Groupe> groups;

	@ManyToOne
	private Admin admin;
	
}
