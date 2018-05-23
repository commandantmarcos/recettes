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
}
