package com.ldnr.recettes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class RecipeDAO extends DAO implements IRecipeDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { DBHelper.RECIPE_ID_RECIPE, DBHelper.RECIPE_NAME_RECIPE, DBHelper.RECIPE_URL_PICTURE, DBHelper.RECIPE_TOTAL_TIME, DBHelper.RECIPE_TYPE_ID_TYPE, DBHelper.STEP_ID_STEP, DBHelper.RECIPE_SERVINGS_COUNT };
    private Recipe recipe;
    private UserDAO daoUser;

    public RecipeDAO(Context context) {
        super(context);
        daoUser = new UserDAO(context);
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
        List<Recipe> list = new ArrayList<>();
        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_RECIPE_NAME, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        while(!res.isAfterLast()) {
            int i = res.getInt(res.getColumnIndex(dbHelper.RECIPE_ID_RECIPE));
            String name = res.getString(res.getColumnIndex(dbHelper.RECIPE_NAME_RECIPE));
            String url = res.getString(res.getColumnIndex(dbHelper.RECIPE_URL_PICTURE));
            float time = res.getFloat(res.getColumnIndex(dbHelper.RECIPE_TOTAL_TIME));
            int serbing = res.getInt(res.getColumnIndex(dbHelper.RECIPE_SERVINGS_COUNT));
            User user = daoUser.find(i);


            list.add(new Recipe(i, name, url, time, serbing, user));
            //lists.add(new Ingredient(res.getInt(res.getColumnIndex(dbHelper.INGREDIENT_ID_INGREDIENT))), res.getString(res.getColumnIndex(dbHelper.INGREDIENT_NAME)));

            res.moveToNext();
        }
        res.close();

        return list;
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