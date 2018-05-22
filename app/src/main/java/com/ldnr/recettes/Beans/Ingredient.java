package com.ldnr.recettes.Beans;

public class Ingredient {

    private int id_ingredient;
    private String name_ingr;

    public int getId_ingredient() {
        return id_ingredient;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public String getName_ingr() {
        return name_ingr;
    }

    public void setName_ingr(String name_ingr) {
        this.name_ingr = name_ingr;
    }

    public Ingredient(int id_ingredient, String name_ingr) {

        this.id_ingredient = id_ingredient;
        this.name_ingr = name_ingr;
    }

    public Ingredient(int id_ingredient) {

        this.id_ingredient = id_ingredient;
    }
}
