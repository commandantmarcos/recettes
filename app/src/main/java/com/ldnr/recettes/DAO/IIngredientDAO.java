package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Ingredient;

import java.util.List;

public interface IIngredientDAO {
    public Ingredient create(Ingredient new_ingredient);
    public Ingredient find(int id);
    public Ingredient find(String name);
    public List<Ingredient> findAll(String name);
    public List<Ingredient> findAll(int id);
    public void update(Ingredient new_ingredient);
    public void delete(Ingredient old_ingredient);
}
