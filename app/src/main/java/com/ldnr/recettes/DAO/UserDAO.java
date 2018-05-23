package com.ldnr.recettes.DAO;

import android.content.ContentValues;
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


    @Override
    public User create(User new_user) {
        setInitialValues(new_user);

        return null;
    }

    @Override
    public User find(int id) {
        return null;
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
    public List<User> findAll() {
        List<Word> words = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Word.TABLE_NAME + " ORDER BY " +
                Word.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.setId(cursor.getInt(cursor.getColumnIndex(Word.COLUMN_ID)));
                word.setWord(cursor.getString(cursor.getColumnIndex(Word.COLUMN_WORD)));
                word.setTimestamp(cursor.getString(cursor.getColumnIndex(Word.COLUMN_TIMESTAMP)));

                words.add(word);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return words list
        return words;
        return null;
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
