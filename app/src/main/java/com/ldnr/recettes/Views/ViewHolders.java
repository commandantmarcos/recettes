package com.ldnr.recettes.Views;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.R;
import com.squareup.picasso.Picasso;


public class ViewHolders extends RecyclerView.ViewHolder {

    private TextView recipe_name;
    private TextView servings_count;
    private ImageView recipe_pic;

    public ViewHolders(View itemView) {
        super(itemView);

        recipe_name = itemView.findViewById(R.id.recipe_name);
        servings_count = itemView.findViewById(R.id.servings_count);
        recipe_pic = itemView.findViewById(R.id.recipe_pic);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Recipe recipe){
        recipe_name.setText(recipe.getName());
        servings_count.setText("Personne : " + Integer.toString(recipe.getServings_count()));

        // Ici nous utilisons Picasso binder l'image depuis une URL à notre imageView.
        Picasso.with(recipe_pic.getContext()).load(recipe.getUrl_picture()).centerCrop().fit().into(recipe_pic);
        Log.d("NAME RECIPE", recipe.getUrl_picture());

    }
}
