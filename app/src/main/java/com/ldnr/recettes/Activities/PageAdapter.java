package com.ldnr.recettes.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class PageAdapter extends FragmentPagerAdapter {

    private int id_recipe;

    public PageAdapter(FragmentManager mgr, int id_recipe) {
        super(mgr);
        this.id_recipe = id_recipe;
        Log.e("CLIQUE !!!!", Integer.toString(id_recipe));
    }

    @Override
    public int getCount() {
        Log.e("GETCOUNT****** !!!!", Integer.toString(id_recipe));
        return(3);
    }

    @Override
    public RecipeFragment getItem(int id_recipe) {
        return(RecipeFragment.newInstance(id_recipe));
    }
}
