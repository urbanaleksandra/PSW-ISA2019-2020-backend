package dto;

import java.util.Set;

import model.Clinic;

public class ClinicDTO {

	private Long id;
	//private ClinicalCenterDTO clinicalCenter;
	private ClinicAdministratorDTO clinicAdministratorDTO;
	
	private String name;
	private String adress;
	private int pricelist;
	private String description;
	private String profit;
	
	
	public ClinicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClinicDTO(Clinic clinic) {
		this(clinic.getName(),clinic.getAdress(),clinic.getPricelist(),clinic.getDescription(),clinic.getProfit());
	}
	
	public ClinicDTO(String name, String adress, int pricelist, String description, String profit) {
		super();
		this.name = name;
		this.adress = adress;
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
