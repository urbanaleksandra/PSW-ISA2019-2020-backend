package com.dto;

import com.model.Clinic;

public class ClinicDTO {

	private Long id;
	//private ClinicalCenterDTO clinicalCenter;
	private ClinicAdministratorDTO clinicAdministratorDTO;
	
	private String name;
	private String address;
	private int pricelist;
	private String description;
	private int profit;
	
	
	public ClinicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClinicDTO(Clinic clinic) {
		this(clinic.getName(),clinic.getAddress(),clinic.getPricelist(),clinic.getDescription(),clinic.getProfit());
	}
	
	public ClinicDTO(String name, String address, int pricelist, String description, int profit) {
		super();
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
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

	public Long getId() {
		return id;
	}
}
