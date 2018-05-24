package com.ldnr.recettes.Beans;

public class Step {

    private int id_step;
    private int step_num;
    private String url_step;
    private String step_description;
    private int id_recipe;

    public Step() {
    }

    public Step(int id_step, int step_num, String url_step, String step_description, int id_recipe) {
        this.id_step = id_step;
        this.step_num = step_num;
        this.url_step = url_step;
        this.step_description = step_description;
        this.id_recipe = id_recipe;
    }

    public int getId_step() {
        return id_step;
    }

    public void setId_step(int id_step) {
        this.id_step = id_step;
    }

    public int getStep_num() {
        return step_num;
    }

    public void setStep_num(int step_num) {
        this.step_num = step_num;
    }

    public String getUrl_step() {
        return url_step;
    }

    public void setUrl_step(String url_step) {
        this.url_step = url_step;
    }

    public String getStep_description() {
        return step_description;
    }

    public void setStep_description(String step_description) {
        this.step_description = step_description;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }
}
