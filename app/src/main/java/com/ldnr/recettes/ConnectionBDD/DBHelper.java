/**
 * @author: GROUPE 3  : PREEL Pauline
 * @date: 22/05/2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.ConnectionBDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "DB_CookingMania.db";

    // TABLE UNITY
    public static final String TABLE_UNITY_NAME = "Unity";
    public static final String UNITY_ID_UNITY = "id_unity";
    public static final String UNITY_UNITY_TYPE = "unity_type";

    // TABLE RECIPE_TYPE
    public static final String TABLE_RECIPE_TYPE_NAME = "Recipe_type";
    public static final String RECIPE_TYPE_ID_TYPE = "id_type";
    public static final String RECIPE_TYPE_TYPE_NAME = "type_name";

    // TABLE INGREDIENT
    public static final String TABLE_INGREDIENT_NAME = "Ingredient";
    public static final String INGREDIENT_ID_INGREDIENT = "id_ingredient";
    public static final String INGREDIENT_NAME = "name";

    // TABLE STEP
    public static final String TABLE_STEP_NAME = "Step";
    public static final String STEP_ID_STEP = "id_step";
    public static final String STEP_STEP_NUM = "step_num";
    public static final String STEP_URL_STEP = "url_step";
    public static final String STEP_STEP_DESCRIBE = "step_describe";

    // TABLE RECIPE
    public static final String TABLE_RECIPE_NAME = "Recipe";
    public static final String RECIPE_ID_RECIPE = "id_recipe";
    public static final String RECIPE_NAME_RECIPE = "name_recipe";
    public static final String RECIPE_URL_PICTURE = "url_picture";
    public static final String RECIPE_TOTAL_TIME = "total_time";
    public static final String RECIPE_SERVINGS_COUNT = "servings_count";

    // TABLE USER
    public static final String TABLE_USER_NAME = "User";
    public static final String USER_ID_USER = "id_user";
    public static final String USER_LOGIN = "login";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";

    // TABLE HAVE
    public static final String TABLE_HAVE_NAME = "Have";
    public static final String HAVE_INGR_COUNT = "ingr_count";

    /****************************
     ****** SQLite QUERIES ******
     ****************************/

    /****** CREATE TABLES ******/
    private static final String SQL_CREATE_UNITY = " CREATE TABLE " + TABLE_UNITY_NAME + " ( " +
            UNITY_ID_UNITY + " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            UNITY_UNITY_TYPE + " text NOT NULL) ";

    private static final String SQL_CREATE_RECIPE_TYPE = " CREATE TABLE " + TABLE_RECIPE_TYPE_NAME + " ( " +
            RECIPE_TYPE_ID_TYPE + " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            RECIPE_TYPE_TYPE_NAME + " text NOT NULL) ";

    private static final String SQL_CREATE_INGREDIENT = " CREATE TABLE " + TABLE_INGREDIENT_NAME + " ( " +
            INGREDIENT_ID_INGREDIENT + " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            INGREDIENT_NAME + " text NOT NULL) ";

    private static final String SQL_CREATE_STEP = " CREATE TABLE " + TABLE_STEP_NAME + " ( " +
            STEP_ID_STEP + " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            STEP_STEP_NUM + " integer NOT NULL, " +
            STEP_URL_STEP + " text NOT NULL, " +
            STEP_STEP_DESCRIBE + " text NOT NULL, " +
            RECIPE_ID_RECIPE + " integer NOT NULL, " +
            " CONSTRAINT Recipe_id_FK FOREIGN KEY (" + RECIPE_ID_RECIPE + ") REFERENCES Recipe_type(" + RECIPE_ID_RECIPE + ") ON DELETE CASCADE )";

    private static final String SQL_CREATE_RECIPE = " CREATE TABLE " + TABLE_RECIPE_NAME + " ( " +
            RECIPE_ID_RECIPE + " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            RECIPE_NAME_RECIPE + " text NOT NULL, " +
            RECIPE_URL_PICTURE + " text NOT NULL, " +
            RECIPE_TOTAL_TIME + " real NOT NULL, " +
            RECIPE_TYPE_ID_TYPE + " integer NOT NULL, " +
            RECIPE_SERVINGS_COUNT + " integer NOT NULL, " +
            " CONSTRAINT Recipe_Recipe_type_FK FOREIGN KEY (" + RECIPE_TYPE_ID_TYPE + ") REFERENCES Recipe_type(" + RECIPE_TYPE_ID_TYPE + ") ON DELETE CASCADE )";

    private static final String SQL_CREATE_USER = " CREATE TABLE " + TABLE_USER_NAME + " ( " +
            USER_ID_USER + " integer PRIMARY KEY AUTOINCREMENT, " +
            USER_LOGIN + " text NOT NULL, " +
            USER_EMAIL + " text NOT NULL, " +
            USER_PASSWORD + " text NOT NULL, " +
            RECIPE_ID_RECIPE + " integer, " +
            " CONSTRAINT User_Recipe_FK FOREIGN KEY (" + RECIPE_ID_RECIPE + ") REFERENCES Recipe(" + RECIPE_ID_RECIPE + ") ) ";

    private static final String SQL_CREATE_HAVE = " CREATE TABLE " + TABLE_HAVE_NAME + " ( " +
            INGREDIENT_ID_INGREDIENT + " integer NOT NULL, " +
            RECIPE_ID_RECIPE + " integer NOT NULL, " +
            UNITY_ID_UNITY + " integer, " +
            HAVE_INGR_COUNT + " integer NOT NULL, " +
            " CONSTRAINT Have_PK PRIMARY KEY (" + INGREDIENT_ID_INGREDIENT + "," + RECIPE_ID_RECIPE + "," + UNITY_ID_UNITY + ")," +
            " CONSTRAINT Have_Ingredient_FK FOREIGN KEY (" + INGREDIENT_ID_INGREDIENT + ") REFERENCES Ingredient(" + INGREDIENT_ID_INGREDIENT + ")," +
            " CONSTRAINT Have_Recipe0_FK FOREIGN KEY (" + RECIPE_ID_RECIPE + ") REFERENCES Recipe(" + RECIPE_ID_RECIPE + "), " +
            " CONSTRAINT Have_Unity1_FK FOREIGN KEY (" + UNITY_ID_UNITY + ") REFERENCES Unity(" + UNITY_ID_UNITY + ") ) ";

    /****** DROP TABLES ******/
    private static final String SQL_DROP_UNITY = "DROP TABLE IF EXISTS " + TABLE_UNITY_NAME + " ; ";
    private static final String SQL_DROP_RECIPE_TYPE = "DROP TABLE IF EXISTS " + TABLE_RECIPE_TYPE_NAME + " ; ";
    private static final String SQL_DROP_INGREDIENTS = "DROP TABLE IF EXISTS " + TABLE_INGREDIENT_NAME + " ; ";
    private static final String SQL_DROP_STEP = "DROP TABLE IF EXISTS " + TABLE_STEP_NAME + " ; ";
    private static final String SQL_DROP_RECIPE = "DROP TABLE IF EXISTS " + TABLE_RECIPE_NAME + " ; ";
    private static final String SQL_DROP_USER = "DROP TABLE IF EXISTS " + TABLE_USER_NAME + " ; ";
    private static final String SQL_DROP_HAVE = "DROP TABLE IF EXISTS " + TABLE_HAVE_NAME + " ; ";

    /**
     * Constructeur par défaut du DBHelper
     * @param context Contexte de l'application
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 34);
    }

    /**
     * Méthode appelée lors de la création de la BDD
     * @param db BDD utilisée
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("PRAGMA foreign_keys=ON;");
        db.execSQL(SQL_CREATE_UNITY);
        db.execSQL(SQL_CREATE_RECIPE_TYPE);
        db.execSQL(SQL_CREATE_INGREDIENT);
        db.execSQL(SQL_CREATE_STEP);
        db.execSQL(SQL_CREATE_RECIPE);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_HAVE);

        createRecipe(db);
        createUnity(db);
        createType(db);
        setSqlCreateIngredient(db);
        createStep(db);
        createHave(db);
        gameTestUser(db);

    }

    /**
     * Méthode permettant de supprimer puis recréer la BDD lors d'un changement de version
     * @param db BDD utilisée
     * @param oldVersion Numéro de l'ancienne version
     * @param newVersion Numéro de la nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_UNITY);
        db.execSQL(SQL_DROP_RECIPE_TYPE);
        db.execSQL(SQL_DROP_INGREDIENTS);
        db.execSQL(SQL_DROP_STEP);
        db.execSQL(SQL_DROP_RECIPE);
        db.execSQL(SQL_DROP_USER);
        db.execSQL(SQL_DROP_HAVE);
        onCreate(db);
    }

    public void gameTestUser(SQLiteDatabase db){

        ContentValues value = new ContentValues();
        List<String> name = new ArrayList<>();
        List<String> email = new ArrayList<>();
        List<String> password = new ArrayList<>();

        name.add("Clémentine");
        name.add("Edgard");
        name.add("Sauron");

        email.add("clementine@mail.com");
        email.add("edgard@mail.com");
        email.add("sauron@mail.com");

        password.add("1234512345");
        password.add("2345623456");
        password.add("3456734567");

        for(int i = 0; i < name.size(); i++){
            value.put(USER_LOGIN, name.get(i));
            value.put(USER_EMAIL, email.get(i));
            value.put(USER_PASSWORD, password.get(i));
            if(i  == 1 || i == 2)
                value.put(RECIPE_ID_RECIPE, i);
            db.insert(TABLE_USER_NAME, null, value);
        }
    }

    public void createUnity(SQLiteDatabase db){
        ContentValues value = new ContentValues();

        List<String> unity = new ArrayList<>();

        unity.add("ml");
        unity.add("cl");
        unity.add("dl");
        unity.add("l");
        unity.add("mg");
        unity.add("g");
        unity.add("cac");
        unity.add("cas");

        for(int i = 0; i < unity.size(); i++){
            value.put(UNITY_UNITY_TYPE, unity.get(i));
            db.insert(TABLE_UNITY_NAME, null, value);
        }
    }

    public void createType(SQLiteDatabase db){
        ContentValues value = new ContentValues();

        value.put(RECIPE_TYPE_TYPE_NAME, "Entrée");
        value.put(RECIPE_TYPE_TYPE_NAME, "Plat");
        value.put(RECIPE_TYPE_TYPE_NAME, "Dessert");

        db.insert(TABLE_RECIPE_TYPE_NAME, null, value);
    }

    public void setSqlCreateIngredient(SQLiteDatabase db){
        ContentValues value = new ContentValues();
        List<String> name = new ArrayList<>();

        name.add("Pain");
        name.add("Carotte");
        name.add("Pomme de terre");
        name.add("Celeri");
        name.add("Sucre");
        name.add("Farine");
        name.add("Sel");
        name.add("Oeuf");
        name.add("Beurre");
        name.add("Huile");
        name.add("Chocolat");

        for(int i = 0; i < name.size(); i++){
            value.put(INGREDIENT_NAME, name.get(i));
            db.insert(TABLE_INGREDIENT_NAME, null, value);
        }
    }

    public void createStep(SQLiteDatabase db){
        ContentValues value = new ContentValues();
        List<String> name = new ArrayList<>();
        List<String> url = new ArrayList<>();
        List<String> description = new ArrayList<>();
        List<String> id_recipe = new ArrayList<>();

        name.add("1");
        url.add("https://image.afcdn.com/recipe/20131023/45431_w600.jpg");
        description.add("Couper votre pain en tranche");
        id_recipe.add("1");

        name.add("2");
        url.add("http://www.regal.fr/sites/art-de-vivre/files/styles/large/public/r30_tartine-beurre_fo.jpg?itok=LdDAuwla");
        description.add("Etaler le beurre sur votre tranche");
        id_recipe.add("1");

        name.add("1");
        url.add("https://image.afcdn.com/recipe/20131023/45431_w600.jpg");
        description.add("Couper votre pain en tranche");
        id_recipe.add("2");

        name.add("2");
        url.add("https://img2.topsante.com/var/topsante/storage/images/nutrition-et-recettes/equilibre-alimentaire/conseils-dietetiques/nutrition-une-pate-a-tartiner-maison-allegee-10783/88298-2-fre-FR/Nutrition-une-pate-a-tartiner-maison-allegee_width1024.jpg");
        description.add("Etaler votre chocolat bio fait maison sur votre tranche de pain bio et fait maison");
        id_recipe.add("2");

        for(int i = 0; i < name.size(); i++){
            value.put(STEP_STEP_NUM, name.get(i));
            value.put(STEP_URL_STEP, url.get(i));
            value.put(STEP_STEP_DESCRIBE, description.get(i));
            value.put(RECIPE_ID_RECIPE, Integer.parseInt(id_recipe.get(i)));
            db.insert(TABLE_STEP_NAME, null, value);
        }
    }

    public void createHave(SQLiteDatabase db){

        ContentValues value = new ContentValues();

        value.put(INGREDIENT_ID_INGREDIENT, 1);
        value.put(RECIPE_ID_RECIPE, 1);
        value.put(HAVE_INGR_COUNT, 1);

        db.insert(TABLE_HAVE_NAME, null, value);

        value.put(INGREDIENT_ID_INGREDIENT, 9);
        value.put(RECIPE_ID_RECIPE, 1);
        value.put(HAVE_INGR_COUNT, 1);

        db.insert(TABLE_HAVE_NAME, null, value);

    }

    public void createRecipe(SQLiteDatabase db){

        ContentValues value = new ContentValues();

        value.put(RECIPE_NAME_RECIPE, "Tartine de beurre");
        value.put(RECIPE_URL_PICTURE, "http://www.regal.fr/sites/art-de-vivre/files/styles/large/public/r30_tartine-beurre_fo.jpg?itok=LdDAuwla");
        value.put(RECIPE_TOTAL_TIME, 3.5);
        value.put(RECIPE_SERVINGS_COUNT, 4);
        value.put(RECIPE_TYPE_ID_TYPE, 3);

        db.insert(TABLE_RECIPE_NAME, null, value);

        value.put(RECIPE_NAME_RECIPE, "Tartine de chocolat");
        value.put(RECIPE_URL_PICTURE, "http://www.regal.fr/sites/art-de-vivre/files/styles/large/public/r30_tartine-beurre_fo.jpg?itok=LdDAuwla");
        value.put(RECIPE_TOTAL_TIME, 3.5);
        value.put(RECIPE_SERVINGS_COUNT, 2);
        value.put(RECIPE_TYPE_ID_TYPE, 3);

        db.insert(TABLE_RECIPE_NAME, null, value);

    }
}