package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private boolean authenticated;
	@Column(nullable = false)
	private String description;

	@JsonBackReference
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Singular("drug")
	private Set<Drug> drug;

	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Appointment appointment;

	@OneToOne(fetch = FetchType.LAZY)
	private Nurse nurse;

	public Recipe() {
	}

	public Recipe(Long id, boolean authenticated, String description, Set<Drug> drug, Appointment appointment, Nurse nurse) {
		this.id = id;
		this.authenticated = authenticated;
		this.description = description;
		this.drug = drug;
		this.appointment = appointment;
		this.nurse = nurse;
	}
}
