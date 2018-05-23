/**
 * @author: GROUPE 3
 * @date: Mai 2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Recipe;

import java.util.List;

public interface IRecipeDAO {

    // CREATE
    public Recipe create(Recipe new_recipe);

    // READ
    public Recipe find(int id);
    public Recipe find(String name);
    public List<Recipe> findAll(String name);
    public List<Recipe> findAll(int id);
    public List<Recipe> findAll();

    // UPDATE
    public void update(Recipe new_recipe);

    // DELETE
    public void delete(Recipe old_recipe);
}
