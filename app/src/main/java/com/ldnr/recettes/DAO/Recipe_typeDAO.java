package com.ldnr.recettes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Recipe_type;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.List;

public class Recipe_typeDAO extends DAO implements IRecipe_typeDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { dbHelper.INGREDIENT_ID_INGREDIENT, dbHelper.RECIPE_ID_RECIPE, dbHelper.UNITY_ID_UNITY, dbHelper.HAVE_INGR_COUNT };
    private Recipe_type recipe_type = null;

    public Recipe_typeDAO(Context context) {
        super(context);
    }

    @Override
    public Recipe_type create(Recipe_type new_recipe_type) {
        return null;
    }

    @Override
    public Recipe_type find(int id) {
        return null;
    }

    @Override
    public Recipe_type find(String name) {
        return null;
    }

    @Override
    public List<Recipe_type> findAll(String name) {
        return null;
    }

    @Override
    public List<Recipe_type> findAll(int id) {
        return null;
    }

    @Override
    public List<Recipe_type> findAll() {
        return null;
    }

    @Override
    public void update(Recipe_type new_recipe_type) {

    }

    @Override
    public void delete(Recipe_type old_recipe_type) {

    }

    public ContentValues getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(Recipe_type recipe_type) {
        this.initialValues = initialValues;

        initialValues.put(dbHelper.INGREDIENT_ID_INGREDIENT, new_have.getIngredient().getId_ingredient());

        // TODO : Réussir à récupérer l'id-recipe !!
        // contentValues.put(dbHelper.RECIPE_ID_RECIPE, new_have.getIngredient());

        initialValues.put(dbHelper.UNITY_ID_UNITY, new_have.getUnity() );
        initialValues.put(dbHelper.HAVE_INGR_COUNT, new_have.getNumber() );
    }
}
