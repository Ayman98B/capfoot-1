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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



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

	private boolean progress = true;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "championship")
	@ToString.Exclude
	private List<Groupe> groups;


	@ManyToOne
	private Admin admin;

}
