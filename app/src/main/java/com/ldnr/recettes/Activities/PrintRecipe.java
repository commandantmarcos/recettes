/**
 * Actually it work, but not finish
 */

package com.ldnr.recettes.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ldnr.recettes.R;

public class PrintRecipe extends AppCompatActivity {

    private int id_recipe;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.print_recipe);

            Intent intent = getIntent();
            id_recipe = Integer.parseInt(intent.getStringExtra("id"));

            this.configureViewPager();
        }

        private void configureViewPager(){
            ViewPager pager = (ViewPager)findViewById(R.id.recipe_viewpager);
            pager.setAdapter(new PageAdapter(getSupportFragmentManager(), id_recipe));
        }
}

/**
public class PrintRecipe extends AppCompatActivity {
    private TextView idView;
    private ImageView url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_recipe);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String urlS = intent.getStringExtra("url");

        idView = findViewById(R.id.recipe_name);
        url = findViewById(R.id.pic);
        idView.setText(name);

        Picasso.with(url.getContext()).load(urlS).centerCrop().fit().into(url);

    }
}**/

