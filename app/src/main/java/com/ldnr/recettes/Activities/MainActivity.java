

package com.ldnr.recettes.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.R;
import com.ldnr.recettes.Views.Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private RecyclerView recyclerView;
	private List<Recipe> recipes = new ArrayList<>();

        @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		Recipe r = new Recipe(1, "Marmelade", "https://www.telegraph.co.uk/travel/destination/article130148.ece/ALTERNATES/w620/parisguidetower.jpg", 3, 4);
		Recipe r1 = new Recipe(2, "Orange", "https://www.mesinspirationsculinaires.com/wp-content/uploads/2014/12/marmelade-dorange-1.jpg", 3, 4);

		recyclerView = findViewById(R.id.recyclerView);
		recipes.add(r);
		recipes.add(r1);
		int displayMode = getResources().getConfiguration().orientation;

		if(displayMode == 1) {
			//définit l'agencement des cellules, ici de façon verticale, comme une ListView
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
		} else {

			//pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
			recyclerView.setLayoutManager(new GridLayoutManager(this,2));
		}

		//puis créer un MyAdapter, lui fournir notre liste de villes.
		//cet adapter servira à remplir notre recyclerview
		recyclerView.setAdapter(new Adapter(recipes));


    }
}
