package com.ldnr.recettes.Beans;

public class Have {

    private Ingredient ingredient;
    private String number;
    private String unity;

    public Have(Ingredient ingredient, String number, String unity) {

        this.ingredient = ingredient;
        this.number = number;
        this.unity = unity;
    }

    public Have(Ingredient ingredient){
        this.ingredient = ingredient;
    }

    public Have() {

    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
}
