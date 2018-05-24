/**
 * Actually it work, but not finish
 */

package com.ldnr.recettes.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.DAO.RecipeDAO;
import com.ldnr.recettes.R;
import com.squareup.picasso.Picasso;

public class PrintRecipe extends AppCompatActivity {

    private TextView recipeTitle;
    private ImageView recipePicture;
    private TextView recipeDetails;
    private TextView ingTitle;
    private ListView listIng;
    private ListView listStep;

    Recipe recipe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_recipe);

        // Get widgets
        recipeTitle = findViewById(R.id.p_recipe_title);
        recipePicture = findViewById(R.id.p_recipe_picture);
        recipeDetails = findViewById(R.id.p_recipe_description);
        ingTitle = findViewById(R.id.p_recipe_ing);
        listIng = findViewById(R.id.p_recipe_ing_list);
        listStep = findViewById(R.id.p_recipe_step_list);

        // Get Extra
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        // Get Recipe clicked
        RecipeDAO daoRecipe = new RecipeDAO(this.getBaseContext());
        recipe = daoRecipe.find(id);

        // Set text and image for the recipe
        recipeTitle.setText(recipe.getName());
        Picasso.with(recipePicture.getContext()).load(recipe.getUrl_picture()).centerCrop().fit().into(recipePicture);

        // Construction of string for the recipe's details
        StringBuilder buffer = new StringBuilder();
        buffer.append("Recette ");
        buffer.append(recipe.getDish_type().getType_name());
        buffer.append("\n");
        buffer.append("Pour ");
        buffer.append(recipe.getServings_count());
        buffer.append(" personnes\n");
        buffer.append("Postée par : ");
        buffer.append(recipe.getUser().getLogin());
        recipeDetails.setText(buffer);

        //
        ArrayAdapter<String> tabIng = new ArrayAdapter<>(listIng.getContext(), R.layout.my_ingredient, R.id.my_ing_text);
        for (Have haveIng : recipe.getListIngredient()) {
            StringBuilder ing = new StringBuilder();
            ing.append(haveIng.getNumber());
            ing.append(" ");
            ing.append(haveIng.getUnity());
            ing.append(" de ");
            ing.append(haveIng.getIngredient().getName_ingr());
            tabIng.add(ing.toString());
        }
        listIng.setAdapter(tabIng);
/**
        ArrayAdapter<Step> tabStep = new ArrayAdapter<>(listIng.getContext(), R.layout.my_ingredient);
        for (Step step : recipe.getSteps()) {


            recipeTitle.setText(recipe.getName());
            Picasso.with(recipePicture.getContext()).load(recipe.getUrl_picture()).centerCrop().fit().into(recipePicture);


            this.initialValues.put(dbHelper.STEP_ID_STEP, step.getId_step() );
        }
        listStep.setAdapter(tabStep);**/


    }

    /**
     * Méthode appelée lorsque l'utilisateur clique sur le bouton RETURN de la liste d'oiseaux
     * @param view
     */
    public void onReturnClicked(View view) {
        // Poss remplacement par :
        this.finish();
    }
}