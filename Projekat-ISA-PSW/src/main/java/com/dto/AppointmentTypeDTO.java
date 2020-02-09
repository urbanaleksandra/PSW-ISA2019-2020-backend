package com.dto;

public class AppointmentTypeDTO {

    private Long id;

    private String name ;

    public AppointmentTypeDTO(String name) {
        this.name = name;
    }

    public AppointmentTypeDTO() {
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
}
