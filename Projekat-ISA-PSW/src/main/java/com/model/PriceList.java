package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Clinic clinic;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private AppointmentType appointmentType;

    @Column(name = "price", nullable = false)
    private int price ;

    public PriceList() {
    }

    public PriceList(Clinic clinic, AppointmentType appointmentType, int price) {
        this.clinic = clinic;
        this.appointmentType = appointmentType;
        this.price = price;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
