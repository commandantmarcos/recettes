/**
 * @author: GROUPE 3  : PREEL Pauline
 * @date: 22/05/2018
 * @brief: Projet : Application de livre de recettes
 *          Programme permettant de créer, consulter, mettre à jour et supprimer des recettes.
 *          Programme destiné aux élèves de la LDNR.
 *          Projet du Module Android / LDNR / Marc Abeille
 */

package com.ldnr.recettes.ConnectionBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
            STEP_STEP_DESCRIBE + " text NOT NULL) ";

    private static final String SQL_CREATE_RECIPE = " CREATE TABLE " + TABLE_RECIPE_NAME + " ( " +
            RECIPE_ID_RECIPE + " integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            RECIPE_NAME_RECIPE + " text NOT NULL, " +
            RECIPE_URL_PICTURE + " text NOT NULL, " +
            RECIPE_TOTAL_TIME + " real NOT NULL, " +
            RECIPE_TYPE_ID_TYPE + " integer NOT NULL, " +
            STEP_ID_STEP + " integer NOT NULL, " +
            RECIPE_SERVINGS_COUNT + " integer NOT NULL, " +
            " CONSTRAINT Recipe_Recipe_type_FK FOREIGN KEY (" + RECIPE_TYPE_ID_TYPE + ") REFERENCES Recipe_type(" + RECIPE_TYPE_ID_TYPE + "), " +
            " CONSTRAINT Recipe_Step0_FK FOREIGN KEY (" + STEP_ID_STEP + ") REFERENCES Step(" + STEP_ID_STEP + ") ) ";

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
        super(context, DATABASE_NAME, null, 1);
    }

    /**
     * Méthode appelée lors de la création de la BDD
     * @param db BDD utilisée
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_UNITY);
        db.execSQL(SQL_CREATE_RECIPE_TYPE);
        db.execSQL(SQL_CREATE_INGREDIENT);
        db.execSQL(SQL_CREATE_STEP);
        db.execSQL(SQL_CREATE_RECIPE);
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_HAVE);
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
}