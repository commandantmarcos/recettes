package com.ldnr.recettes.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.R;
import com.ldnr.recettes.TestForm.ConfirmLoginForm;

import java.util.Objects;

    public class CreateLoginActivity extends AppCompatActivity {

        private String localLogin;
        private String localPassword;
        private String localEmail;
        private Resources res;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_login);
        }


        public void onValidLoginClicked(View view) {

            EditText login = findViewById( R.id.user_login );
            EditText email = findViewById( R.id.user_mail );
            EditText password = findViewById( R.id.user_password );
            EditText confirm_password = findViewById( R.id.user_confirm_password );

            String userLogin = login.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = login.getText().toString();
            String userConfirmPassword = confirm_password.getText().toString();

            User user = new User();
            boolean error = false;
            String erreur = "f";
            ConfirmLoginForm newLogin = new ConfirmLoginForm();
            user = newLogin.ConfirmNewLoginForm(login, email, password, confirm_password,erreur, error);

            if(error== true){

               // Toast.makeText(this,erreur,Toast.LENGTH_LONG);

                Log.d("erreur inscription", erreur) ;
            }

            else {
                //dao.insert(user);
                //finish();

                Intent createLoginIntent = new Intent(this, PrintRecipe.class);
                startActivity(createLoginIntent);
            }
        }
    }


