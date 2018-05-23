package com.ldnr.recettes.Activities;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ldnr.recettes.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private String localLogin;
    private String localPassword;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


        // get login password from resources
      /*  res = getResources();
        localLogin = res.getString( R.string.local_login );
        localPassword = res.getString( R.string.local_password );
    }

    public void onLoginClicked(View view) {
        // On recupere les edit Text
        EditText eLogin = findViewById( R.id.user_login );
        EditText ePassw = findViewById( R.id.user_password );

        // On recupère leur contenu
        String userLogin = eLogin.getText().toString();
        String userPassword = ePassw.getText().toString();

        // On teste login / mdp
        if (Objects.equals( userLogin, localLogin ) && Objects.equals( userPassword, localPassword )) {
            // Si OK un petit message
            Toast.makeText( this, res.getString(R.string.success_login), Toast.LENGTH_LONG).show();
            // Et on lance l'activité PrintRecipe
            Intent connectIntent = new Intent(this, PrintRecipe.class);
            startActivity( connectIntent );
        } else {
            // Sinon on met les champs à blanc
            eLogin.setText( null );
            ePassw.setText( null );
            // Et on prévient l'utilisateur
            Toast.makeText( this, res.getString(R.string.error_login), Toast.LENGTH_LONG).show();
        }
    }*/

    public void onLoginClicked(View view) {

        Intent loginIntent = new Intent(this, PrintRecipe.class);
        startActivity(loginIntent);

    }
}


