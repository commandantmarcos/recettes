/**
 * @author: GROUPE 3
 * @date: Mai 2018
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

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.List;

import static java.sql.DriverManager.println;

public class RecipeDAO extends DAO implements IRecipeDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { DBHelper.RECIPE_ID_RECIPE, DBHelper.RECIPE_NAME_RECIPE, DBHelper.RECIPE_URL_PICTURE, DBHelper.RECIPE_TOTAL_TIME, DBHelper.RECIPE_TYPE_ID_TYPE, DBHelper.STEP_ID_STEP, DBHelper.RECIPE_SERVINGS_COUNT };
    private Recipe recipe;

    public RecipeDAO(Context context) {
        super(context);
    }

    @Override
    public Recipe create(Recipe new_recipe) {
        DAO.open();

        setInitialValues(new_recipe);

        try {
            if (database.insertOrThrow(DBHelper.TABLE_HAVE_NAME, null, initialValues) == -1) {
                recipe = null;
            } else {
                recipe = new_recipe;
            }
        } catch (SQLException se) {
            //Gérer le traitement en cas d'erreur d'insertion
            println(se.getMessage());
        } finally {
            DAO.close();
        }

        return recipe;

    }

    @Override
    public Recipe find(int id) {
        return null;
    }

    @Override
    public Recipe find(String name) {
        return null;
    }

    @Override
    public List<Recipe> findAll(String name) {
        return null;
    }

    @Override
    public List<Recipe> findAll(int id) {
        return null;
    }

    @Override
    public List<Recipe> findAll() {

        return null;
    }

    @Override
    public void update(Recipe new_recipe) {
        open();

        setInitialValues(new_recipe);

        database.update(DBHelper.TABLE_RECIPE_NAME, this.initialValues, DBHelper.RECIPE_ID_RECIPE + " = ?", new String[]{String.valueOf(new_recipe.getId_recipe())});
    }

    @Override
    public void delete(Recipe old_recipe) {
        open();
        database.delete(DBHelper.TABLE_RECIPE_NAME, DBHelper.RECIPE_ID_RECIPE + " = ?",
                new String[]{String.valueOf(old_recipe.getId_recipe())});
        close();
    }

    public ContentValues getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(Recipe recipe) {
        this.initialValues = new ContentValues();
        this.initialValues.put(DBHelper.RECIPE_ID_RECIPE, recipe.getId_recipe());
        this.initialValues.put(DBHelper.RECIPE_NAME_RECIPE, recipe.getName());
        this.initialValues.put(DBHelper.RECIPE_URL_PICTURE, recipe.getUrl_picture());
        this.initialValues.put(DBHelper.RECIPE_TOTAL_TIME, recipe.getTotal_time() );
        this.initialValues.put(DBHelper.RECIPE_TYPE_ID_TYPE, recipe.dish_type.getId_type());

        for (Step step : List<Step> steps) {
            this.initialValues.put(DBHelper.STEP_ID_STEP, recipe.steps.getId_step() );
        }

        this.initialValues.put(DBHelper.RECIPE_SERVINGS_COUNT, recipe.getServings_count() );
    }
}