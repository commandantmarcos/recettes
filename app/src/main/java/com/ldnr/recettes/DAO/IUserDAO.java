/**
 * @author: GROUPE 3
 * @date: Mai 2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import com.ldnr.recettes.Beans.User;

import java.util.List;

public interface IUserDAO {
    // CREATE
    public User create(User new_user);

    // READ
    public User find(int id);
    public User find(String name);
    public List<User> findAll(String name);
    public List<User> findAll(int id);
    public List<User> findAll();
    public void update(User new_user);

    // DELETE
    public void delete(User old_user);
}
