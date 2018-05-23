

package com.ldnr.recettes.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ldnr.recettes.Beans.Ingredient;
import com.ldnr.recettes.Beans.Recipe;
import com.ldnr.recettes.Beans.Step;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.DAO.IngredientDAO;
import com.ldnr.recettes.DAO.RecipeDAO;
import com.ldnr.recettes.DAO.StepDAO;
import com.ldnr.recettes.DAO.UserDAO;
import com.ldnr.recettes.R;
import com.ldnr.recettes.Views.Adapter;
import com.ldnr.recettes.Views.ItemClickSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	private RecyclerView recyclerView;
	private List<Recipe> recipes = new ArrayList<>();
	private List<User> users = new ArrayList<>();
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<Step> steps = new ArrayList<>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		UserDAO daoU = new UserDAO(this);
		IngredientDAO ingrDAO = new IngredientDAO(this);
		RecipeDAO daoRecipe = new RecipeDAO(this);
        StepDAO daoStep = new StepDAO(this);
		ingredients = ingrDAO.findAll();
		users = daoU.findAll();
		recipes = daoRecipe.findAll();
		steps = daoStep.findAll();


		for(int i = 0; i < steps.size(); i++) {
            Log.d("AFFICHAGE STEP", steps.get(i).getStep_description());
        }

		recyclerView = findViewById(R.id.recyclerView);

		int displayMode = getResources().getConfiguration().orientation;

		if (displayMode == 1) {
			//définit l'agencement des cellules, ici de façon verticale, comme une ListView
			recyclerView.setLayoutManager(new LinearLayoutManager(this));
		} else {

			//pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
			recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
		}

		//puis créer un MyAdapter, lui fournir notre liste de villes.
		//cet adapter servira à remplir notre recyclerview
		recyclerView.setAdapter(new Adapter(recipes));
		configureOnClickRecyclerView();

	}

	// 1 - Configure item click on RecyclerView
	private void configureOnClickRecyclerView() {
		ItemClickSupport.addTo(recyclerView, R.layout.cell_cards)
				.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
					@Override
					public void onItemClicked(RecyclerView recyclerView, int position, View v) {
						/*	Catch item position	*/
						Adapter a = new Adapter(recipes);
						Recipe r = a.getPosition(position);
						Intent intent = new Intent(recyclerView.getContext(), PrintRecipe.class);

						/*	Send recipe to next Activity	*/
						intent.putExtra("id", Integer.toString(r.getId_recipe()));
						intent.putExtra("name", r.getName());
						intent.putExtra("url", r.getUrl_picture());

						startActivity(intent);
					}
				});
	}

	public void onConnectClicked(View view) {
		Intent loginIntent = new Intent(this, LoginActivity.class);
		startActivity(loginIntent);

	}
	public void onCreateLoginClicked(View view) {
		Intent createLoginIntent = new Intent(this, CreateLoginActivity.class);
		startActivity(createLoginIntent);

	}


}