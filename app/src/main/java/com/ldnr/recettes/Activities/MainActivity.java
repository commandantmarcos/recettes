

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
import com.ldnr.recettes.Activities.Update;

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
	private int mposition;

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
		configureOnClickRecyclerView();
		configureOnClickLongRecyclerView();
		OnClickLongRecyclerView();


	}

	// 1 - Configure item click on RecyclerView
	private void configureOnClickRecyclerView() {
		Log.e("allez on y croit","kjqdhgjhhdghhjifh///////////////////////////");
		ItemClickSupport.addTo(recyclerView, R.layout.cell_cards)
				.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
					@Override
					public void onItemClicked(RecyclerView recyclerView, int position, View v) {
						Log.e("ça plane pour moi","k///////////////drgh///////////");
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
						 //recipeDAO.delete();
					}
				});
	}


	/*	Gestion du clique long	*/
	private void configureOnClickLongRecyclerView() {
		ItemClickSupport.addTo(recyclerView, R.layout.cell_cards)
				.setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {

					@Override
					public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {

						Adapter a = new Adapter(recipes);
						Recipe r = a.getPosition(position);
						mposition = r.getId_recipe();
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
		builder.setPositiveButton("oui", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(recyclerView.getContext(), MainActivity.class);
				daoRecipe.delete(mposition);
				startActivity(intent);
			}
		});
		builder.setNegativeButton("non", null);

		// create and show the alert dialog
		AlertDialog dialog = builder.create();


		dialog.show();

		//deleteOnClickRecyclerView();

	}

	public void onConnectClicked(View view) {
		Intent loginIntent = new Intent(this, LoginActivity.class);
		startActivity(loginIntent);

	}
	public void onCreateLoginClicked(View view) {
		Intent createLoginIntent = new Intent(this, CreateLoginActivity.class);
		startActivity(createLoginIntent);

	}
	public void showUpdate() {
		Intent intent = new Intent(this, Update.class);
		intent.putExtra("id", mposition);
		startActivity(intent);
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
				//RecipeDAO recipeDao = new RecipeDAO(this);
				switch (which) {
					case 0: // updade
						showUpdate();

						Log.e("re al con ", "ftyzigyf//////rhz**********tyje----------");
						break;
					case 1: // delete
							showAlertDialogButtonClicked();

						daoRecipe.delete(mposition);
						break;
					case 2: // cancel
						break;

				}
			}
		});

		// create and show the alert dialog
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	//commentaire

}