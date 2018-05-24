

package com.ldnr.recettes.Activities;

import com.ldnr.recettes.DAO.IRecipeDAO;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import com.ldnr.recettes.Beans.Recipe_type;
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
	private RecipeDAO daoRecipe;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		daoRecipe = new RecipeDAO(this);
		recipes = daoRecipe.findAll();

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
		//configureOnClickRecyclerView();
		configureOnClickLongRecyclerView();

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

	private void OnClickLongRecyclerView() {
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

						startActivity(intent);
					}
				});
	}

	private void deleteOnClickRecyclerView() {
		ItemClickSupport.addTo(recyclerView, R.layout.cell_cards)
				.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
					@Override
					public void onItemClicked(RecyclerView recyclerView, int position, View v) {
						/*	Catch item position	*/
						Log.d("test delete", "******************////////////////////////");
						Adapter a = new Adapter(recipes);
						Recipe r = a.getPosition(position);
						 RecipeDAO recipeDAO = new RecipeDAO(recyclerView.getContext());
						 recipeDAO.delete(5);
					}
				});
	}


	/*	Gestion du clique long	*/
	private void configureOnClickLongRecyclerView() {
		ItemClickSupport.addTo(recyclerView, R.layout.cell_cards)
				.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
						showAlertDialog(recyclerView);
						Log.d("JE SUIS DANS LONGCLICK", "#################################################################################");
						return false;

					}
				});
	}



		public void showAlertDialogButtonClicked() {

			// setup the alert builder
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Suppression");
			builder.setMessage("Souhaitez vous vraiment supprimer");
			// add the buttons
			builder.setPositiveButton("oui", null);
			builder.setNegativeButton("non", null);

			// create and show the alert dialog
			AlertDialog dialog = builder.create();

			dialog.show();
			deleteOnClickRecyclerView();

		}

	public void onConnectClicked(View view) {
		Intent loginIntent = new Intent(this, LoginActivity.class);
		startActivity(loginIntent);


	}
	public void onCreateLoginClicked(View view) {
		//Intent createLoginIntent = new Intent(this, CreateLoginActivity.class);
		//startActivity(createLoginIntent);

	}


	public void showAlertDialog(View view) {

		// setup the alert builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Choisissez votre action");

		// add a list
		String[] choice = {"update", "delete", "cancel"};
		builder.setItems(choice, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Recipe recipe = new Recipe();
				RecipeDAO recipeDao = new RecipeDAO(view.getContext());
				switch (which) {
					case 0: // updade

						//OnClickLongRecyclerView();
						configureOnClickRecyclerView();
					break;
					case 1: // delete

						showAlertDialogButtonClicked();
						alacon();

						Log.d("retest delete ","*****************////////////////////////***************");
                  //daoRecipe.delete(r.getId_recipe());

					break;
					case 2: // cancel
						Log.d("j'ai choisi cancel", "rienrienrienrien");

						break;

				}
			}
		});

		// create and show the alert dialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void alacon(){
		Log.d("je suis un log a la con" , "********************       ***********");
	}


}