package com.ldnr.recettes.DAO;

import android.content.Context;
import android.database.Cursor;

import com.ldnr.recettes.Beans.Ingredient;
import com.ldnr.recettes.Beans.User;

import java.util.ArrayList;
import java.util.List;

public class IngredientDAO extends DAO implements IIngredientDAO {
    /**
     * Constructor
     *
     * @param context
     */
    public IngredientDAO(Context context) {
        super(context);
    }

    @Override
    public Ingredient create(Ingredient new_ingredient) {
        return null;
    }

    @Override
    public Ingredient find(int id) {
        return null;
    }

    @Override
    public Ingredient find(String name) {
        return null;
    }

    @Override
    public List<Ingredient> findAll(String name) {
        return null;
    }

    @Override
    public List<Ingredient> findAll(int id) {
        List<Ingredient> lists = new ArrayList<>();
        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_INGREDIENT_NAME + " WHERE " + id + " = " + dbHelper.RECIPE_ID_RECIPE, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        while(!res.isAfterLast()) {
            int i = res.getInt(res.getColumnIndex(dbHelper.INGREDIENT_ID_INGREDIENT));
            String name = res.getString(res.getColumnIndex(dbHelper.INGREDIENT_NAME));
            lists.add(new Ingredient(i, name));
            //lists.add(new Ingredient(res.getInt(res.getColumnIndex(dbHelper.INGREDIENT_ID_INGREDIENT))), res.getString(res.getColumnIndex(dbHelper.INGREDIENT_NAME)));

            res.moveToNext();
        }
        res.close();
        return lists;
    }

    @Override
    public List<Ingredient> findAll() {
        List<Ingredient> lists = new ArrayList<>();
        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_INGREDIENT_NAME, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        while(!res.isAfterLast()) {
            int i = res.getInt(res.getColumnIndex(dbHelper.INGREDIENT_ID_INGREDIENT));
            String name = res.getString(res.getColumnIndex(dbHelper.INGREDIENT_NAME));
            lists.add(new Ingredient(i, name));
            //lists.add(new Ingredient(res.getInt(res.getColumnIndex(dbHelper.INGREDIENT_ID_INGREDIENT))), res.getString(res.getColumnIndex(dbHelper.INGREDIENT_NAME)));

            res.moveToNext();
        }
        res.close();
        return lists;
    }

    @Override
    public void update(Ingredient new_ingredient) {

    }

    @Override
    public void delete(Ingredient old_ingredient) {

    }
}
