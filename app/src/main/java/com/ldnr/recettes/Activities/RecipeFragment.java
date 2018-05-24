package com.ldnr.recettes.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.DAO.RecipeDAO;
import com.ldnr.recettes.DAO.UserDAO;
import com.ldnr.recettes.R;
import com.squareup.picasso.Picasso;

public class RecipeFragment extends Fragment {

    private static final String KEY_ID_RECIPE = "id";

    private Recipe recipe;

    public RecipeFragment() {
    }

    public static RecipeFragment newInstance(int id_recipe) {

        RecipeFragment recipeFragment = new RecipeFragment();
        Log.e("NEWINSTANCEMF** !!!!", Integer.toString(id_recipe));
        Bundle args = new Bundle();
        //args.putInt(KEY_ID_RECIPE, id_recipe);
        args.putInt("key", id_recipe);
        Log.e("CLIQUE !!!!", Integer.toString(args.getInt("key")));
        recipeFragment.setArguments(args);

        return(recipeFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Get layouts and widgets
        View result = inflater.inflate(R.layout.fragment_recipe, container, false);
        LinearLayout rootView = (LinearLayout) result.findViewById(R.id.recipe_slide);

        ImageView imageView_picture = (ImageView) result.findViewById(R.id.recipe_slideimg);
        TextView textView_title = (TextView) result.findViewById(R.id.recipe_title);
        TextView textView_description = (TextView) result.findViewById(R.id.recipe_description);

        int id_recipe = -1;

        try {
            // Get data from the Bundle
            id_recipe = getArguments().getInt(KEY_ID_RECIPE, -1);
        }catch (Exception e){
            // TODO : gestion exception
        }

        // Get datas from database
        RecipeDAO daoRecipe = new RecipeDAO(this.getContext());
        recipe = daoRecipe.find(id_recipe);
        User user;
        UserDAO daoUser = new UserDAO(this.getContext());
        user = daoUser.find(id_recipe);

        String url = recipe.getUrl_picture();

        // Set the recipe's picture on ImageView
        Picasso.with(imageView_picture.getContext()).load(url).centerCrop().fit().into(imageView_picture);

        // Set the recipe's name on TextView
        textView_title.setText(recipe.getName());

        // Set the recipe's details on TextView
        StringBuilder buffer = new StringBuilder();
        //buffer.append("Recette ");
        //buffer.append(recipe.getDish_type().getType_name());
        //buffer.append("\n");
        buffer.append("Pour ");
        buffer.append(Integer.toString(recipe.getServings_count()));
        buffer.append(" personnes\n");
        buffer.append("Postée par : ");
//        buffer.append(user.getLogin());
        textView_description.setText(buffer);

        Log.e(getClass().getSimpleName(), "onCreateView called for recipe number " + id_recipe);

        return result;
    }
}