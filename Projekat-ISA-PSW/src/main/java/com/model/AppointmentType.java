package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppointmentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name ;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<PriceList> priceList = new HashSet<PriceList>();

    public AppointmentType() {
    }

    public AppointmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
