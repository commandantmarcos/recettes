package com.ldnr.recettes.DAO;

import android.content.Context;
import android.database.Cursor;

import com.ldnr.recettes.Beans.Recipe_type;

import java.util.ArrayList;
import java.util.List;

public class UnitsDAO extends DAO {
    private Cursor cursor;
    /**
     * Constructor
     *
     * @param context
     */
    public UnitsDAO(Context context) {
        super(context);
    }

    public List<String> findAll(){
        List<String> units = new ArrayList<>();
        cursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_UNITY_NAME, null);
        cursor.moveToFirst();

        if( cursor != null && cursor.moveToFirst() ){
            while(!cursor.isAfterLast()) {
                units.add(new String((cursor.getString(cursor.getColumnIndex(dbHelper.UNITY_UNITY_TYPE)))) );
                cursor.moveToNext();
            }
            cursor.close();
        }

        return units;
    }

}
