package com.ldnr.recettes.DAO;

import android.content.Context;
import android.database.Cursor;

import com.ldnr.recettes.Beans.Step;

import java.util.ArrayList;
import java.util.List;

public class StepDAO extends DAO implements IStepDAO {


    /**
     * Constructor
     *
     * @param context
     */
    public StepDAO(Context context) {
        super(context);
    }

    @Override
    public Step create(Step new_step) {
        return null;
    }

    @Override
    public Step find(int id) {
        Step step;

        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_STEP_NAME + " WHERE " + dbHelper.STEP_ID_STEP + " = " + id,  null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :

        int id_step = res.getInt(res.getColumnIndex(dbHelper.STEP_ID_STEP));
        int num = res.getInt(res.getColumnIndex(dbHelper.STEP_STEP_NUM));
        String url = res.getString(res.getColumnIndex(dbHelper.STEP_URL_STEP));
        String description = res.getString(res.getColumnIndex(dbHelper.STEP_STEP_DESCRIBE));


        step = new Step(id_step, num, url, description);

        res.close();

        return step;
    }

    @Override
    public Step find(String name) {
        return null;
    }

    @Override
    public List<Step> findAll(String name) {
        return null;
    }

    @Override
    public List<Step> findAll(int id) {
        List<Step> steps = new ArrayList<>();
        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_STEP_NAME + " WHERE " + dbHelper.RECIPE_ID_RECIPE + " = " + id, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        while(!res.isAfterLast()) {

            int id_step = res.getInt(res.getColumnIndex(dbHelper.STEP_ID_STEP));
            int num = res.getInt(res.getColumnIndex(dbHelper.STEP_STEP_NUM));
            String url = res.getString(res.getColumnIndex(dbHelper.STEP_URL_STEP));
            String description = res.getString(res.getColumnIndex(dbHelper.STEP_STEP_DESCRIBE));


            steps.add(new Step(id_step, num, url, description));

            res.moveToNext();
        }
        res.close();
        return steps;
    }

    @Override
    public List<Step> findAll() {
        List<Step> steps = new ArrayList<>();
        Cursor res = dbHelper.getReadableDatabase().rawQuery( "select * from " + dbHelper.TABLE_STEP_NAME, null );
        // On positionne notre curseur en première position
        res.moveToFirst();
        // Tant qu’on est pas arrivé à la fin de nos enregistrements :
        while(!res.isAfterLast()) {

            int id_step = res.getInt(res.getColumnIndex(dbHelper.STEP_ID_STEP));
            int num = res.getInt(res.getColumnIndex(dbHelper.STEP_STEP_NUM));
            String url = res.getString(res.getColumnIndex(dbHelper.STEP_URL_STEP));
            String description = res.getString(res.getColumnIndex(dbHelper.STEP_STEP_DESCRIBE));


            steps.add(new Step(id_step, num, url, description));

            res.moveToNext();
        }
        res.close();
        return steps;
    }

    @Override
    public void update(Step new_step) {

    }

    @Override
    public void delete(Step old_step) {

    }


    public void delete(List<Step> old_step) {
        open();
        for(int i = 0; i < old_step.size(); i++) {
            database.delete(dbHelper.TABLE_STEP_NAME, dbHelper.STEP_ID_STEP + " = ?",
                    new String[]{String.valueOf(old_step.get(i).getId_step())});
        }
        close();

    }
}
