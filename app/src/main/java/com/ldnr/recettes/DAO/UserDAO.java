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

import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class UserDAO extends DAO implements IUserDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { dbHelper.USER_ID_USER, dbHelper.USER_LOGIN, dbHelper.USER_EMAIL, dbHelper.USER_PASSWORD, dbHelper.RECIPE_ID_RECIPE };
    private User user;

    /**
     * Constructor
     *
     * @param context
     */
    public UserDAO(Context context) {
        super(context);
    }


    @Override
    public User create(User new_user) {
        open();

        setInitialValues(new_user);

        try {
            if (database.insertOrThrow(dbHelper.TABLE_HAVE_NAME, null, initialValues) == -1) {
                user = null;
            } else {
                user = new_user;
            }
        } catch (SQLException se) {
            //Gérer le traitement en cas d'erreur d'insertion
            println(se.getMessage());
        } finally {
            close();
        }

        return user;
    }

    @Override
    public User find(int id) {
        open();
        cursor = database.rawQuery( "select * from " + dbHelper.TABLE_USER_NAME, null );
        // On positionne notre curseur en première position
        cursor.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        if( cursor != null && cursor.moveToFirst() ) {
            user = new User(cursor.getInt(cursor.getColumnIndex(dbHelper.USER_ID_USER)),(cursor.getString(cursor.getColumnIndex(dbHelper.USER_LOGIN))),
                    (cursor.getString(cursor.getColumnIndex(dbHelper.USER_EMAIL))), (cursor.getString(cursor.getColumnIndex(dbHelper.USER_PASSWORD)));
        }
        cursor.close();

        return user;
    }

    @Override
    public User find(String name) {
        return null;
    }

    @Override
    public List<User> findAll(String name) {
        return null;
    }

    @Override
    public List<User> findAll(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
         Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_USER_NAME, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        while(!res.isAfterLast()) {
            users.add(new User(res.getInt(res.getColumnIndex(dbHelper.USER_ID_USER)),(res.getString(res.getColumnIndex(dbHelper.USER_LOGIN))),
                    res.getString(res.getColumnIndex(dbHelper.USER_EMAIL)), res.getString(res.getColumnIndex(dbHelper.USER_PASSWORD))));

            res.moveToNext();
        }
        res.close();
        return users;
    }

    @Override
    public void update(User new_user) {
        database.update(dbHelper.TABLE_USER_NAME, initialValues, " id_user = ? ", new String[]{Integer.toString(new_user.getId())});
    }

    @Override
    public void delete(User old_user) {
        open();
        database.delete(dbHelper.TABLE_USER_NAME, dbHelper.USER_ID_USER + " = ?", new String[]{String.valueOf(old_user.getId())});
        close();
    }

    public ContentValues getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(User user) {
        initialValues = new ContentValues();
        initialValues.put(dbHelper.USER_LOGIN, user.getLogin());
        initialValues.put(dbHelper.USER_EMAIL, user.getEmail());
        initialValues.put(dbHelper.USER_PASSWORD, user.getPassword() );
    }
}
