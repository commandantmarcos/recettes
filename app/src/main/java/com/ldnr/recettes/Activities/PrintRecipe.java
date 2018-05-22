/**
 * Actually it work, but not finish
 */

package com.ldnr.recettes.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldnr.recettes.R;
import com.squareup.picasso.Picasso;

public class PrintRecipe extends AppCompatActivity {
    private TextView idView;
    private ImageView url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_recipe);

        /*  Catch value from putExtras by MainActivity  */
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String urlS = intent.getStringExtra("url");

        idView = findViewById(R.id.recipe_name);
        url = findViewById(R.id.pic);
        idView.setText(name);

        Picasso.with(url.getContext()).load(urlS).centerCrop().fit().into(url);

    }
}
