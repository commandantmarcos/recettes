package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Recipe_type;

import java.util.List;

public interface IRecipe_typeDAO {
    public Recipe_type create(Recipe_type new_recipe_type);
    public Recipe_type find(int id);
    public Recipe_type find(String name);
    public List<Recipe_type> findAll(String name);
    public List<Recipe_type> findAll(int id);
    public void update(Recipe_type new_recipe_type);
    public void delete(Recipe_type old_recipe_type);
}
