package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//lombok
//@Getter
//@Setter
//@NoArgsConstructor
@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClinicalCenter clinicalCenter;

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	private ClinicAdministrator clinicAdministrator;

	@JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name="clinicAdministrator_id")
    private Set<ClinicAdministrator> clinicAdministrator = new HashSet<ClinicAdministrator>();

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name="clinicAdministrator_id")
    private Set<Doctor> doctors = new HashSet<Doctor>();

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
    @Column(name = "rating", nullable = false)
    private int rating;

    public int getRating() {
        return rating;
    }

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Surgery> surgeries = new HashSet<Surgery>();

    public void setRating(int rating) {
        this.rating = rating;
    }

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

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
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
