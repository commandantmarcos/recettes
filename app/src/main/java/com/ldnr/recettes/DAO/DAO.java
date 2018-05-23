/**
 * @author: GROUPE 3  : PREEL Pauline
 * @date: 22/05/2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.List;

public abstract class DAO {

    protected static SQLiteDatabase database;
    protected static DBHelper dbHelper;

    /**
     * Constructor
     * @param context
     */
    public DAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    /**
     * Method to open database
     * @throws SQLException
     */
    public static void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * Method to close database
     */
    public static void close() {
        dbHelper.close();
    }

    /**
     * Method to insert data on database
     * @param object Instance of object
     * @return Object created
     */
    public abstract Object create(Object object);

    /**
     * Method to find data with id
     * @param id Int of searched data's id
     * @return Object found
     */
    public abstract Object find(int id);

    /**
     * Method to find data with name
     * @param name String of searched data's name
     * @return Object found
     */
    public abstract Object find(String name);

    /**
     * Method to find all datas with alike name
     * @param name String of searched data's name
     * @return List of objects found
     */
    public abstract List<Object> findAll(String name);

    /**
     * Method to find all datas with id of type of dishes
     * @param id  Int of searched type's id
     * @return List of objects found
     */
    public abstract List<Object> findAll(int id);

    /**
     * Method to update data with new entries
     * @param object Instance of data modified
     */
    public abstract void update(Object object);

    /**
     * Method to delete data on database
     * @param object Instance of object to delete
     */
    public abstract void delete(Object object);

}
