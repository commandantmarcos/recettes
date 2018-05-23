/**
 * @author: GROUPE 3  : PREEL Pauline
 * @date: 22/05/2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;


import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class HaveDAO extends DAO implements IHaveDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { DBHelper.INGREDIENT_ID_INGREDIENT, DBHelper.RECIPE_ID_RECIPE, DBHelper.UNITY_ID_UNITY, DBHelper.HAVE_INGR_COUNT };
    private Have have = null;

    /**
     * Constructor by default
     * @param context
     */
    public HaveDAO(Context context) {
        super(context);
    }

    @Override
    public Have create(Have new_have) {
        open();
        setInitialValues(new_have);

        try {
            if (database.insertOrThrow(DBHelper.TABLE_HAVE_NAME, null, initialValues) == -1) {
                have = null;
            } else {
                have = new_have;
            }
        } catch (SQLException se) {
            // Gérer le traitement en cas d'erreur d'insertion
            println(se.getMessage());
        } finally {
            close();
        }
        return have;
    }

    @Override
    public List<Have> findAll(int id_recipe) {
        List<Have> listHave = new ArrayList<>();
        cursor = database.rawQuery("SELECT "+ allColumns + " FROM " + DBHelper.TABLE_HAVE_NAME + " WHERE " + DBHelper.RECIPE_ID_RECIPE + " LIKE " + id + " ; " , null );
        cursor.moveToFirst();

        if( cursor != null && cursor.moveToFirst() ){
            while(cursor.isAfterLast()) {
                // TODO : accéder à ingrédient !!
                //listHave.add(new Have((cursor.getClass(cursor.getColumnIndex(DBHelper.INGREDIENT_ID_INGREDIENT))), (cursor.getInt(cursor.getColumnIndex(DBHelper.RECIPE_ID_RECIPE))), (cursor.getInt(cursor.getColumnIndex(DBHelper.UNITY_ID_UNITY)))), (cursor.getInt(cursor.getColumnIndex(DBHelper.HAVE_INGR_COUNT)))) );
                cursor.moveToNext();
            }
            cursor.close();
        }
        return listHave;
    }

    @Override
    public void update(int id) {
        database.update(DBHelper.TABLE_HAVE_NAME, initialValues, " id_recipe = ? ", new String[]{Integer.toString(id)});
    }

    @Override
    public void delete(Have object) {

    }

    public ContentValues getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(Have new_have) {
        initialValues = new ContentValues();
        initialValues.put(DBHelper.INGREDIENT_ID_INGREDIENT, new_have.getIngredient().getId_ingredient());

        // TODO : Réussir à récupérer l'id-recipe !!
        // contentValues.put(DBHelper.RECIPE_ID_RECIPE, new_have.getIngredient());

        initialValues.put(DBHelper.UNITY_ID_UNITY, new_have.getUnity());
        initialValues.put(DBHelper.HAVE_INGR_COUNT, new_have.getNumber() );
    }
}
