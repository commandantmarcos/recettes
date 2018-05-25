/**
 * Actually it work, but not finish
 */

package com.ldnr.recettes.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.DAO.HaveDAO;
import com.ldnr.recettes.DAO.RecipeDAO;
import com.ldnr.recettes.DAO.StepDAO;
import com.ldnr.recettes.DAO.UserDAO;
import com.ldnr.recettes.R;
import com.ldnr.recettes.Views.AdapterPrint;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PrintRecipe extends AppCompatActivity {

    private RecipeDAO daoRecipe;
    private HaveDAO daoHave;
    private StepDAO daoStep;
    private UserDAO userDao;

    private List<Have> listHave = new ArrayList<>();
    private List<Step> tabStep = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.print_recipe);

        // Get widgets
        TextView recipeTitle = findViewById(R.id.p_recipe_title);
        ImageView recipePicture = findViewById(R.id.p_recipe_picture);
        TextView recipeDetails = findViewById(R.id.p_recipe_description);
        TextView ingTitle = findViewById(R.id.p_recipe_ing);
        ListView listIng = findViewById(R.id.p_recipe_ing_list);
        RecyclerView listStep = findViewById(R.id.p_recipe_step_list);

        // Get Extra
        Intent intent = getIntent();
        String ids = intent.getStringExtra("id");
        int id = Integer.parseInt(ids);

        // Get Recipe clicked
        daoRecipe = new RecipeDAO(this);
        userDao = new UserDAO(this);

        Recipe recipe = daoRecipe.find(id);
        Log.e("Null Exception ", "###################################### "+ recipe.getName() +" ###########################################");

        User user = userDao.find(id);

        Log.e("Null Exception ", "###################################### "+ id+" ###########################################");

            // Set text and image for the recipe
            recipeTitle.setText(recipe.getName());
            Picasso.with(recipePicture.getContext()).load(recipe.getUrl_picture()).centerCrop().fit().into(recipePicture);

            // Construction of string for the recipe's details
            StringBuilder buffer = new StringBuilder();
            buffer.append("Recette ");
            //buffer.append(recipe.getDish_type().getType_name());
            buffer.append("\n");
            buffer.append("Pour ");
            buffer.append(recipe.getServings_count());
            buffer.append(" personnes\n");
            buffer.append("Postée par : ");
            buffer.append(user.getLogin());
            recipeDetails.setText(buffer);

            //
            ArrayAdapter<String> tabIng = new ArrayAdapter<>(listIng.getContext(), R.layout.my_ingredient, R.id.my_ing_text);

            daoHave = new HaveDAO(this);
            listHave = daoHave.findAll(id);

            for (Have haveIng : listHave) {
                StringBuilder ing = new StringBuilder();
                ing.append(haveIng.getNumber());
                ing.append(" ");
                ing.append(haveIng.getUnity());
                ing.append(" de ");
                ing.append(haveIng.getIngredient().getName_ingr());
                tabIng.add(ing.toString());
            }
            if (!tabIng.isEmpty()) {
                listIng.setAdapter(tabIng); }

        listStep = findViewById(R.id.p_recipe_step_list);
        listStep.setLayoutManager(new LinearLayoutManager(this));

            daoStep = new StepDAO(this);
            tabStep = daoStep.findAll(id);

        listStep.setAdapter(new AdapterPrint(tabStep));

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