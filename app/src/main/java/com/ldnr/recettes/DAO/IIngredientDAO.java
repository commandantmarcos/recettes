/**
 * @author: GROUPE 3
 * @date: Mai 2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Ingredient;

import java.util.List;

public interface IIngredientDAO {

    // CREATE
    public Ingredient create(Ingredient new_ingredient);

    // READ
    public Ingredient find(int id);
    public Ingredient find(String name);
    public List<Ingredient> findAll(String name);
    public List<Ingredient> findAll(int id);
    public List<Ingredient> findAll();

    // UPDATE
    public void update(Ingredient new_ingredient);

    // DELETE
    public void delete(Ingredient old_ingredient);
}
