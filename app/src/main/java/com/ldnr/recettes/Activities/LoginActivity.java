package com.ldnr.recettes.Activities;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.DAO.UserDAO;
import com.ldnr.recettes.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private String localLogin;
    private String localPassword;
    private String login_user ;
    private String password_user;
    private Resources res;
    EditText user_login;
    EditText user_password;
    TextView connect_error;
    User localUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        res = getResources();

        user_login = findViewById(R.id.user_login);
        user_password = findViewById(R.id.user_password);
        connect_error = findViewById(R.id.connect_error);

        user_login = new EditText(this);
        user_password = new EditText(this);
        connect_error = new TextView(this);
    }



    public void onLoginClicked(View view) {
         user_login = findViewById( R.id.user_login );
         user_password = findViewById( R.id.user_password );
         connect_error = findViewById(R.id.connect_error);

        localLogin = user_login.getText().toString();
        localPassword = user_password.getText().toString();

        /**Récupération des login et MDP de l'user pour la comparaison avec la BDD*/
        UserDAO userDao = new UserDAO(view.getContext());
        localUser = userDao.find(user_login.getText().toString());
        login_user = localUser.getLogin();
        password_user = localUser.getPassword();

        if (Objects.equals( localLogin, login_user ) && Objects.equals( localPassword, password_user )) {
            connect_error.setText("Connection réussie!");
            Intent connectIntent = new Intent(this, MainActivity.class);
            startActivity( connectIntent );
        }
        else {
            user_login.setText( null );
            user_password.setText( null );
            connect_error.setText("Veuillez vérifier vos identifiants.");
        }
    }
}


