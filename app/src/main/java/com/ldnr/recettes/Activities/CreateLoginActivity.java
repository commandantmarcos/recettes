package com.ldnr.recettes.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.DAO.UserDAO;
import com.ldnr.recettes.R;
import com.ldnr.recettes.TestForm.ConfirmLoginForm;


    public class CreateLoginActivity extends AppCompatActivity {

        private Resources res;
        TextView error_login;
        TextView error_mail;
        TextView error_pass;
        TextView error_confirm;
        TextView empty_field;
        EditText login;
        EditText email;
        EditText password;
        EditText confirm_password;
        public static int b = 0;
        User user;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_login);
            res = getResources();

            error_login = findViewById(R.id.login_error);
            error_mail = findViewById(R.id.mail_error);
            error_pass = findViewById(R.id.pass_error);
            error_confirm = findViewById(R.id.confirm_error);
            empty_field = findViewById(R.id.empty_error);

            login = new EditText(this);
            email = new EditText(this);
            password = new EditText(this);
            confirm_password = new EditText(this);
        }


        public void onValidLoginClicked(View view) {

            login = findViewById(R.id.user_login);
            email = findViewById(R.id.user_mail);
            password = findViewById(R.id.user_password);
            confirm_password = findViewById(R.id.user_confirm_password);

            TextView error_login = findViewById(R.id.login_error);
            TextView error_mail = findViewById(R.id.mail_error);
            TextView error_pass = findViewById(R.id.pass_error);
            TextView error_confirm = findViewById(R.id.confirm_error);
            TextView empty_field = findViewById(R.id.empty_error);

            error_login.setText("");
            error_mail.setText("");
            error_pass.setText("");
            error_confirm.setText("");
            empty_field.setText("");

            ConfirmLoginForm newLogin = new ConfirmLoginForm();
            newLogin.ConfirmNewLoginForm(login, email, password, confirm_password);

            switch (b) {
                case 1:
                    error_login.setText("Le login doit avoir au moins 3 caractères");
                    b=0;
                    break;
                case 2:
                    error_mail.setText("L'adresse mail n'est pas valide");
                    b=0;
                    break;
                case 3:
                    error_pass.setText("Ressaisissez le pass et la confirmation \n (3 caractères minimum)");
                    b=0;
                    break;
                case 4:
                    error_confirm.setText("Les passwords sont différents");
                    b=0;
                    break;
                case 5:
                    empty_field.setText("Veuillez remplir tous les champs");
                    b=0;
                    break;
                case 0:
                    Log.d("tag","?????????????????????????????????");
                    user = new User(login.getText().toString(), email.getText().toString(),
                               password.getText().toString());
                    UserDAO userCreate = new UserDAO(this);
                    user = userCreate.create(user);
                    Intent homeIntent = new Intent(this, MainActivity.class);
                    startActivity(homeIntent);
                    break;
            }
        }
    }


