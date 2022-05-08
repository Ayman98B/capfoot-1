package com.capgemini.capfoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

	@Getter
	@Setter
	@Column(nullable = false)
	private boolean progress = true;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "championship")
	@ToString.Exclude
	@JsonIgnore
	private List<Groupe> groups;

	@ManyToOne
	private Admin admin;

}
