/**
 * @author: GROUPE 3
 * @date: Mai 2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Recipe_type;

import java.util.List;

public interface IRecipe_typeDAO {

    // CREATE
    public Recipe_type create(Recipe_type new_recipe_type);

    // READ
    public Recipe_type find(int id);
    public Recipe_type find(String name);
    public List<Recipe_type> findAll(String name);
    public List<Recipe_type> findAll(int id);
    public List<Recipe_type> findAll();

    // UPDATE
    public void update(Recipe_type new_recipe_type);

    // DELETE
    public void delete(Recipe_type old_recipe_type);
}
