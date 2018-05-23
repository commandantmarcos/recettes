/**
 * @author: GROUPE 3
 * @date: Mai 2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.Step;

import java.util.List;

public interface IStepDAO {

    // CREATE
    public Step create(Step new_step);

    // READ
    public Step find(int id);
    public Step find(String name);
    public List<Step> findAll(String name);
    public List<Step> findAll(int id);
    public List<Step> findAll();
    public void update(Step new_step);

    // DELETE
    public void delete(Step old_step);
}
