package com.ldnr.recettes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.Recipe_type;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class RecipeDAO extends DAO implements IRecipeDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { dbHelper.RECIPE_ID_RECIPE, dbHelper.RECIPE_NAME_RECIPE, dbHelper.RECIPE_URL_PICTURE, dbHelper.RECIPE_TOTAL_TIME, dbHelper.RECIPE_TYPE_ID_TYPE, dbHelper.STEP_ID_STEP, dbHelper.RECIPE_SERVINGS_COUNT };
    private Recipe recipe;
    private UserDAO daoUser;
    private StepDAO stepDAO;
    private Recipe_typeDAO typeDAO;
    private HaveDAO haveDAO;

    public RecipeDAO(Context context) {
        super(context);
        daoUser = new UserDAO(context);
        stepDAO = new StepDAO(context);
        typeDAO = new Recipe_typeDAO(context);
        haveDAO = new HaveDAO(context);
    }

    @Override
    public Recipe create(Recipe new_recipe) {
        DAO.open();

        setInitialValues(new_recipe);

        try {
            if (database.insertOrThrow(dbHelper.TABLE_HAVE_NAME, null, initialValues) == -1) {
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
        Recipe recipe;
        List<Have> listIngredient;
        List<Step> steps;

        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_RECIPE_NAME + " WHERE " + dbHelper.RECIPE_ID_RECIPE + " = " + id, null );
        // On positionne notre curseur en première position
        //res.moveToFirst();
        if(res.moveToFirst()) {
            int i = res.getInt(res.getColumnIndex(dbHelper.RECIPE_ID_RECIPE));
            String name = res.getString(res.getColumnIndex(dbHelper.RECIPE_NAME_RECIPE));
            String url = res.getString(res.getColumnIndex(dbHelper.RECIPE_URL_PICTURE));
            float time = res.getFloat(res.getColumnIndex(dbHelper.RECIPE_TOTAL_TIME));
            int serving = res.getInt(res.getColumnIndex(dbHelper.RECIPE_SERVINGS_COUNT));
            User user = daoUser.find(i);
            Recipe_type dish_type = typeDAO.find(res.getInt(res.getColumnIndex(dbHelper.RECIPE_TYPE_ID_TYPE)));
            listIngredient = haveDAO.findAll(i);

            steps = stepDAO.findAll(i);


            recipe = new Recipe(i, name, url, time, serving, user, dish_type, listIngredient, steps);
            Log.d("RECIPE_DAO !!!!!!!", "SUCESS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }else{
            Log.d("RECIPE_DAO !!!!!!!", "FAIL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            recipe = new Recipe();
        }

        res.close();

        return recipe;
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
        List<Have> listIngredient;
        List<Step> steps;

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
            Recipe_type dish_type = typeDAO.find(i);
            //Recipe_type dish_type = new Recipe_type();
            listIngredient = haveDAO.findAll(i);

            steps = stepDAO.findAll(i);


            list.add(new Recipe(i, name, url, time, serbing, user, dish_type, listIngredient, steps));
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

        database.update(dbHelper.TABLE_RECIPE_NAME, this.initialValues, dbHelper.RECIPE_ID_RECIPE + " = ?", new String[]{String.valueOf(new_recipe.getId_recipe())});
    }

    @Override
    public void delete(Recipe old_recipe) {
        open();
        Log.d("TAILLE STEP DANS RECIPE: ", Integer.toString(old_recipe.getSteps().size()));
        //Step old_step = stepDAO.find(old_recipe.getSteps().get(0).getId_step());
       /* List<Step> steps = new ArrayList<>();
        List<Step> steps = stepDAO.findAll(old_recipe.getSteps().get(0).getId_step());
        Log.d("DAO_RECIPE8DELETE", "JE RECUPERE L ID DE LA RECIPE SOIT " + Integer.toString(old_recipe.getSteps().get(0).getId_step()));*/
        database.delete(dbHelper.TABLE_RECIPE_NAME, dbHelper.RECIPE_ID_RECIPE + " = ?",
                new String[]{String.valueOf(old_recipe.getId_recipe())});
       /* for(int i = 0; i < steps.size(); i++)
            stepDAO.delete(steps.get(i));*/
        close();
    }
    public void delete(int id) {
        open();
        Cursor res = dbHelper.getWritableDatabase().rawQuery( "delete from " + dbHelper.TABLE_RECIPE_NAME + " WHERE " + dbHelper.RECIPE_ID_RECIPE + " = " + id, null );
        res.moveToFirst();
        //stepDAO.delete(old_recipe.getSteps());
        /*database.delete(dbHelper.TABLE_RECIPE_NAME, dbHelper.RECIPE_ID_RECIPE + " = ?",
                new String[]{String.valueOf(old_recipe.getId_recipe())});*/
       /* for(int i = 0; i < steps.size(); i++)
            stepDAO.delete(steps.get(i));*/
        close();
    }
    public ContentValues getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(Recipe recipe) {
        this.initialValues = new ContentValues();
        this.initialValues.put(dbHelper.RECIPE_ID_RECIPE, recipe.getId_recipe());
        this.initialValues.put(dbHelper.RECIPE_NAME_RECIPE, recipe.getName());
        this.initialValues.put(dbHelper.RECIPE_URL_PICTURE, recipe.getUrl_picture());
        this.initialValues.put(dbHelper.RECIPE_TOTAL_TIME, recipe.getTotal_time() );
        this.initialValues.put(dbHelper.RECIPE_TYPE_ID_TYPE, recipe.getDish_type().getId_type());
        for (Step step : recipe.getSteps()) {
            this.initialValues.put(dbHelper.STEP_ID_STEP, step.getId_step() );
        }
        this.initialValues.put(dbHelper.RECIPE_SERVINGS_COUNT, recipe.getServings_count() );
    }
}