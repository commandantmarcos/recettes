package com.ldnr.recettes.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.ConnectionBDD.DBHelper;

import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO implements IUserDAO {

    private Cursor cursor;
    private ContentValues initialValues;
    private String[] allColumns = { DBHelper.USER_ID_USER, DBHelper.USER_LOGIN, DBHelper.USER_EMAIL, DBHelper.USER_PASSWORD, DBHelper.RECIPE_ID_RECIPE };
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
        setInitialValues(new_user);

        return null;
    }

    @Override
    public User find(int id) {
        User user;
        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_USER_NAME, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :

            user = new User(res.getInt(res.getColumnIndex(dbHelper.USER_ID_USER)),(res.getString(res.getColumnIndex(dbHelper.USER_LOGIN))),
                    res.getString(res.getColumnIndex(dbHelper.USER_EMAIL)), res.getString(res.getColumnIndex(dbHelper.USER_PASSWORD)));

        res.close();

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

    }

    @Override
    public void delete(User old_user) {
        open();
        database.delete(DBHelper.TABLE_USER_NAME, DBHelper.USER_ID_USER + " = ?",
                new String[]{String.valueOf(old_user.getId())});
        database.close();
    }

    public ContentValues getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(User user) {
        initialValues = new ContentValues();
        initialValues.put(DBHelper.USER_ID_USER, user.getId());
        initialValues.put(DBHelper.USER_LOGIN, user.getLogin());
        initialValues.put(DBHelper.USER_EMAIL, user.getEmail());
        initialValues.put(DBHelper.USER_PASSWORD, user.getPassword() );
    }
}
