/**
 * @author: GROUPE 3
 * @date: Mai 2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Have;

import java.util.List;

public interface IHaveDAO {
    // CREATE
    public Have create(Have new_have);

    // READ
    public Have find(int id);
    public Have find(String name);
    public List<Have> findAll(String name);
    public List<Have> findAll(int id_recipe);
    public List<Have> findAll();

    // UPDATE
    public void update(int id_recipe);

    // DELETE
    public void delete(int id_recipe);
}
