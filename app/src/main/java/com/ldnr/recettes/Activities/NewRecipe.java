package com.ldnr.recettes.Activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.ldnr.recettes.Beans.Have;
import com.ldnr.recettes.Beans.Ingredient;
import com.ldnr.recettes.ConnectionBDD.DBHelper;
import com.ldnr.recettes.DAO.IngredientDAO;
import com.ldnr.recettes.DAO.UnitsDAO;
import com.ldnr.recettes.R;

import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends AppCompatActivity {

    private IngredientDAO daoIngr;
    private UnitsDAO unitsDao;
    private List<String> allIngredients = new ArrayList<>();
    private List<String> allUnities = new ArrayList<>();
    private List<Ingredient> ingrs = new ArrayList<>();
    private LinearLayout new_recipe_layout;
    private Spinner ingredients;
    private Spinner unities;
    private EditText number;
    AlertDialog.Builder builder;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe);
        daoIngr = new IngredientDAO(this);
        unitsDao = new UnitsDAO(this);
        ingrs = daoIngr.findAll();
        Spinner ingredients = findViewById(R.id.bbox_ingredients);
        Spinner unities =  findViewById(R.id.bbox_unity);
        EditText number = findViewById(R.id.number);
        Button ajouter = findViewById(R.id.button2);
        Button new_ingr = findViewById(R.id.button);
        builder = new AlertDialog.Builder(this);

        //db = new DBHelper(this);
        new_recipe_layout = findViewById(R.id.newRecipeLayout);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allIngredients);
        ingredients.setAdapter(dataAdapter);

        allUnities = unitsDao.findAll();
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allUnities);
        unities.setAdapter(dataAdapter2);
    }

    public void onAjouterClicked(View view){

        List<Have> recipeList= new ArrayList<>();


        String ingredient = ingredients.getSelectedItem().toString();
        Ingredient current = new Ingredient(ingredient);
        String unity = unities.getSelectedItem().toString();
        String quantity = number.getText().toString();
        recipeList.add(new Have(current, quantity, unity));
        //DAOIngredient.insererIngredient();

        ingredients.setSelection(0);
        unities.setSelection(0);
    }

    public Dialog onnewingrClicked(View view) {

        builder.setView(R.layout.pop_up);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getApplicationContext(), "Nouvel ingrédient ajouté!", Toast.LENGTH_LONG).show();
                //inserer un nouvel ingredient dans la base de données
                //methode needed
            }
        } );
        builder.show();
        return builder.create();
    }

}
