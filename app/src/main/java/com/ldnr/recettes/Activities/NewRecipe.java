package com.ldnr.recettes.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Ingredient;
import com.ldnr.recettes.ConnectionBDD.DBHelper;
import com.ldnr.recettes.R;

import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends AppCompatActivity {

    private DBHelper db;
    private LinearLayout new_recipe_layout;
    private List<Ingredient> allIngredients = new ArrayList<>();
    private List<Unities> allUnities = new ArrayList<>();
    private Spinner ingredients;
    private Spinner unities;
    private EditText number;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);
        Spinner ingredients = (Spinner) findViewById(R.id.bbox_ingredients);
        Spinner unities = (Spinner) findViewById(R.id.bbox_unity);
        EditText number = (EditText) findViewById(R.id.number);
        Button ajouter = (Button) findViewById(R.id.button);

        db = new DBHelper(this);

        new_recipe_layout = findViewById(R.id.newRecipeLayout);

        allIngredients.addAll(db.getallIngredients);
        //allUnities.addAll(db.getAllUnities);

        ingredients = new Spinner(this, allIngredients);
        //unities = new Spinner(this, allUnities);


    }

    @Override
    public void onAjouterClicked(View view){

        List<Have> recipeList= new ArrayList<>();

        String ingredient = ingredients.getSelectedItem().toString();
        String unity = unities.getSelectedItem().toString();
        Integer quantity = Integer.toString(number.getText());

    }

}
