package com.dto;

public class RecipeDTO {

//    id: number = 0;
//    name: string = "";
//    description: string = "";

    private long id;
    private String description;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
