package com.capgemini.capfoot.entity;

import java.time.LocalDate;
import java.util.List;

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
	private Statut statut = Statut.INSCRIPTION;

	@Column(nullable = false)
	private boolean progress = true;

	@OneToMany(mappedBy = "championship")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Groupe> groups;

	@ManyToOne
	private Admin admin;

}
