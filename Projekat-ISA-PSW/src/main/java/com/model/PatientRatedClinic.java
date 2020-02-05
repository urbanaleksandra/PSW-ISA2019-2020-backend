package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(PatientRatedClinicId.class)
public class PatientRatedClinic implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Clinic clinic;

    @Id
    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Patient patient;

    @Column(nullable = true)
    private int ocena;

    public Clinic getClinic() {
        return clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getOcena() {
        return ocena;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public PatientRatedClinic() {
    }

    public PatientRatedClinic(Clinic clinic, Patient patient, int ocena) {
        this.clinic = clinic;
        this.patient = patient;
        this.ocena = ocena;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
