package com.model;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicalCenter clinicalCenter;	
	
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private ClinicAdministrator clinicAdministrator;

	@OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ClinicAdministrator> clinicAdministrator = new HashSet<ClinicAdministrator>();

	@OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HospitalRoom> hospitalRooms = new HashSet<HospitalRoom>();
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "pricelist", nullable = false)
	private int pricelist;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "profit", nullable = false)
	private int profit;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPricelist() {
		return pricelist;
	}
	public void setPricelist(int pricelist) {
		this.pricelist = pricelist;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProfit() {
		return profit;
	}
	public void setProfit(int profit) {
		this.profit = profit;
	}

	public Set<ClinicAdministrator> getClinicAdministrator() {
		return clinicAdministrator;
	}

	public void setClinicAdministrator(Set<ClinicAdministrator> clinicAdministrator) {
		this.clinicAdministrator = clinicAdministrator;
	}

	@Override
	public String toString() {
		return "Clinic{" +
				"id=" + id +
				", clinicalCenter=" + clinicalCenter +
				", clinicAdministrator=" + clinicAdministrator +
				", hospitalRooms=" + hospitalRooms +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", pricelist=" + pricelist +
				", description='" + description + '\'' +
				", profit=" + profit +
				'}';
	}
}
