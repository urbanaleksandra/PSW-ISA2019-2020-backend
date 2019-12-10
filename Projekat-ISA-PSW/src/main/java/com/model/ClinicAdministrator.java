package com.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class ClinicAdministrator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email")
	private String email;


	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Clinic clinic;

	@OneToMany(mappedBy = "clinicAdministrator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HolidayRequest> requests = new HashSet<HolidayRequest>();

	public ClinicAdministrator() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClinicAdministrator(Long id, String username, String password, String email, Clinic clinic, Set<HolidayRequest> requests) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.clinic = clinic;
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "ClinicAdministrator{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", clinic=" + clinic +
				", requests=" + requests +
				'}';
	}
}
