package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Recipe;

import java.util.List;

public interface IRecipeDAO {
    public Recipe create(Recipe new_recipe);
    public Recipe find(int id);
    public Recipe find(String name);
    public List<Recipe> findAll(String name);
    public List<Recipe> findAll(int id);
    public List<Recipe> findAll();
    public void update(Recipe new_recipe);
    public void delete(Recipe old_recipe);
}
