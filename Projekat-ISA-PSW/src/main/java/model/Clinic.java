package model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicalCenter clinicalCenter;	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ClinicAdministrator clinicAdministrator;	
	
	@OneToMany(mappedBy = "clinic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<HospitalRoom> hospitalRooms = new HashSet<HospitalRoom>();
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "adress", nullable = false)
	private String adress;
	@Column(name = "pricelist", nullable = false)
	private int pricelist;
	@Column(name = "description", nullable = false)
	private String description;
	@Column(name = "profit", nullable = false)
	private String profit;
	
	
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
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
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
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	
}
