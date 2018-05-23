package com.ldnr.recettes.Beans;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private int id_recipe;
    private String name_recipe;
    private String url_picture;
    private float total_time;
    private int servings_count;
    private User user;
    private Recipe_type dish_type;
    private List<Have> listIngredient;
    private List<Step> steps;

    public Recipe(int id_recipe, String name_recipe, String url_picture, float total_time, int servings_count, User user, Recipe_type dish_type, List<Have> listIngredient, List<Step> steps) {
        this.id_recipe = id_recipe;
        this.name_recipe = name_recipe;
        this.url_picture = url_picture;
        this.total_time = total_time;
        this.servings_count = servings_count;
        this.user = user;
        this.dish_type = dish_type;
        this.listIngredient = new ArrayList<Have>();
        this.steps = new ArrayList<Step>();
    }

    public Recipe() {

    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

    public void setName(String name) {
        this.name_recipe = name_recipe;
    }

    public void setUrl_picture(String url_picture) {
        this.url_picture = url_picture;
    }

    public void setTotal_time(float total_time) {
        this.total_time = total_time;
    }

    public void setServings_count(int servings_count) {
        this.servings_count = servings_count;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDish_type(Recipe_type dish_type) {
        this.dish_type = dish_type;
    }

    public void setListIngredient(List<Have> listIngredient) {
        this.listIngredient = listIngredient;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public String getName() {
        return name_recipe;
    }

    public String getUrl_picture() {
        return url_picture;
    }

    public float getTotal_time() {
        return total_time;
    }

    public int getServings_count() {
        return servings_count;
    }

    public User getUser() {
        return user;
    }

    public Recipe_type getDish_type() {
        return dish_type;
    }

    public List<Have> getListIngredient() {
        return listIngredient;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
