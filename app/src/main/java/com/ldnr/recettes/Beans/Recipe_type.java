package com.ldnr.recettes.Beans;

public class Recipe_type {

    private int id_type;
    private String type_name;

    public Recipe_type() {
    }

    public Recipe_type(int id_type, String type_name) {
        this.id_type = id_type;
        this.type_name = type_name;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
