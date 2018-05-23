package com.ldnr.recettes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ldnr.recettes.Beans.Recipe_type;

import java.util.ArrayList;
import java.util.List;

public class Recipe_typeDAO extends DAO implements IRecipe_typeDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { dbHelper.RECIPE_TYPE_ID_TYPE, dbHelper.RECIPE_TYPE_TYPE_NAME };
    private Recipe_type recipe_type = null;

    /**
     * Constructor by default
     * @param context
     */
    public Recipe_typeDAO(Context context) {
        super(context);
    }

    @Override
    public Recipe_type create(Recipe_type new_recipe_type) {
        // TODO : Création d'un type de plat
        // Uniquement quand le client aura validé et payé !
        return null;
    }

    @Override
    public Recipe_type find(int id) {
        open();
        Log.d("HERE TYPEDAO !!!!!", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        cursor = database.rawQuery( "select * from " + dbHelper.TABLE_RECIPE_NAME + " WHERE " + dbHelper.RECIPE_ID_RECIPE + " = " + id, null );
        cursor.moveToFirst();

        if( cursor != null && cursor.moveToFirst() ) {
            int idType = (cursor.getInt(cursor.getColumnIndex(dbHelper.RECIPE_TYPE_ID_TYPE)));
            Cursor cursor2 = database.rawQuery("select * from " + dbHelper.TABLE_RECIPE_TYPE_NAME + " WHERE " + dbHelper.RECIPE_TYPE_ID_TYPE + " = " + idType, null);

            if( cursor2 != null && cursor2.moveToFirst() )
                recipe_type = new Recipe_type( idType, (cursor2.getString(cursor2.getColumnIndex(dbHelper.RECIPE_TYPE_TYPE_NAME))) ) ;
        }
        cursor.close();

        return recipe_type;
    }

    @Override
    public Recipe_type find(String name) {
        open();
        cursor = database.rawQuery( "select " + allColumns + " from " + dbHelper.TABLE_USER_NAME + " WHERE " + dbHelper.RECIPE_TYPE_TYPE_NAME + " = " + name + " ; ", null );
        cursor.moveToFirst();

        if( cursor != null && cursor.moveToFirst() ) {
            recipe_type = new Recipe_type( (cursor.getInt(cursor.getColumnIndex(dbHelper.RECIPE_TYPE_ID_TYPE))), (cursor.getString(cursor.getColumnIndex(dbHelper.RECIPE_TYPE_TYPE_NAME))) ) ;
        }
        cursor.close();

        return recipe_type;
    }

    @Override
    public List<Recipe_type> findAll(String name) {
        // TODO : Recherche de tous les types par nom
        // Payable en cash !
        return null;
    }

    @Override
    public List<Recipe_type> findAll(int id) {
        // TODO : Recherche de tous les types par id
        // Possiblité de troquer la fonctionnalité contre un séjour d'un weekend à Londres TCC !
        return null;
    }

    @Override
    public List<Recipe_type> findAll() {
        List<Recipe_type> listType = new ArrayList<>();
        cursor = database.rawQuery("SELECT "+ allColumns + " FROM " + dbHelper.TABLE_RECIPE_TYPE_NAME + " ; " , null );
        cursor.moveToFirst();

        if( cursor != null && cursor.moveToFirst() ){
            while(!cursor.isAfterLast()) {
                listType.add(new Recipe_type((cursor.getInt(cursor.getColumnIndex(dbHelper.RECIPE_TYPE_ID_TYPE))), (cursor.getString(cursor.getColumnIndex(dbHelper.RECIPE_TYPE_TYPE_NAME)))) );
                cursor.moveToNext();
            }
            cursor.close();
        }

        return listType;
    }

    @Override
    public void update(Recipe_type new_recipe_type) {
        // TODO : Mise à jour d'un type de plat
        // Pour le prix d'une rate seulement !
    }

    @Override
    public void delete(Recipe_type old_recipe_type) {
        // TODO : Suppression d'un type de plat
        // Uniquement quand une autre main est mise à la poche !
    }

    /**
     * Méthode permettant de renvoyer les valeurs du type initialisé
     * @return initialValues : ContentValues
     */
    public ContentValues getInitialValues() {
        return initialValues;
    }

    /**
     * Méthode permettant de rentrer les valeurs du type à initialiser
     * @param recipe_type : Recipe_type à créer
     */
    public void setInitialValues(Recipe_type recipe_type) {
        this.initialValues = new ContentValues();
        initialValues.put(dbHelper.RECIPE_TYPE_TYPE_NAME, recipe_type.getType_name());
    }
}
