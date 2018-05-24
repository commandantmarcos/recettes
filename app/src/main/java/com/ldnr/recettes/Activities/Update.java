package com.ldnr.recettes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ldnr.recettes.R;
import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.DAO.RecipeDAO;

public class Update extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

           /*setLayout();
            Intent intent = getIntent();
            int in_recip = intent.getExtra("id");

            RecipeDAO dao = new RecipeDAO(this);
            Recipe r = dao.find(id_recipe);*/


    }

}
