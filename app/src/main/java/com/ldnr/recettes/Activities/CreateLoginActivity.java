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

            EditText login = findViewById(R.id.user_login);
            EditText email = findViewById(R.id.user_mail);
            EditText password = findViewById(R.id.user_password);
            EditText confirm_password = findViewById(R.id.user_confirm_password);

            String userLogin = login.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userConfirmPassword = confirm_password.getText().toString();

            User user = new User();
            ConfirmLoginForm newLogin = new ConfirmLoginForm();
            user = newLogin.ConfirmNewLoginForm(userLogin, userEmail, userPassword, userConfirmPassword);



        }
        public  void onErrorCatch(int i) {
            EditText login = findViewById(R.id.user_login);
            EditText email = findViewById(R.id.user_mail);
            EditText password = findViewById(R.id.user_password);
            EditText confirm_password = findViewById(R.id.user_confirm_password);

            String userLogin = login.getText().toString();
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();
            String userConfirmPassword = confirm_password.getText().toString();

            User user = new User();
            ConfirmLoginForm newLogin = new ConfirmLoginForm();
            user = newLogin.ConfirmNewLoginForm(userLogin, userEmail, userPassword, userConfirmPassword);


            switch (i){
                case ConfirmLoginForm.validPassword( userPassword, userConfirmPassword, user):
                    Toast.makeText( this, res.getString(R.string.error_email), Toast.LENGTH_LONG).show();
                    break;
                case 2:
                     Toast.makeText( this, res.getString(R.string.error_login), Toast.LENGTH_LONG).show();
                     break;
                case 3:
                    Toast.makeText(this, res.getString(R.string.error_password_short), Toast.LENGTH_LONG).show();
                    break;
                case 4:
                    Toast.makeText( this, res.getString(R.string.error_password_wrong), Toast.LENGTH_LONG).show();
                    break;
                case 5:
                    Toast.makeText( this, res.getString(R.string.empty_field), Toast.LENGTH_LONG).show();

            }

        }
    }


