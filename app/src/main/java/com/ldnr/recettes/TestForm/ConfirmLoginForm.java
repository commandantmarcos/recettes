package com.ldnr.recettes.TestForm;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldnr.recettes.Activities.CreateLoginActivity;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.R;


public class ConfirmLoginForm extends AppCompatActivity{

    static int a;
    public static int erreur = 0;
    public int ConfirmNewLoginForm(EditText login, EditText email, EditText password,
                                   EditText confirm_password) {

        try {
            String Login = login.getText().toString();
            validLogin(Login);
        } catch (Exception e) {
            return 1;
        }

        try {
            String Email = email.getText().toString();
            validEmail(Email);
        } catch (Exception e) {
            return 2;
        }

        try {
            String Password = password.getText().toString();
            String ConfirmPassword = confirm_password.getText().toString();
            validPassword(Password, ConfirmPassword);
        } catch (Exception e) {
            return 3;
        }

        return 10;
    }


    /**
     * Valide l'adresse mail saisie.
     */
    public void validEmail(String email) throws Exception {
        if (email != null ) {
            if (email.isEmpty()){
                CreateLoginActivity.b = 5;
                throw new Exception("Merci de saisir une adresse mail.");
            }
            else if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)") ) {
                CreateLoginActivity.b = 2;
                throw new Exception(" non valide.");
            }
        }
    }

    /**
     * Valide les passwword saisis.
     */
    public void validPassword(String password, String confirm_password) throws Exception {

        if(password.isEmpty() || confirm_password.isEmpty()){
            CreateLoginActivity.b = 5;
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
        if(password.trim().length() < 3) {
            CreateLoginActivity.b = 3;
            throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
        }
        if(!password.equals(confirm_password)) {
            CreateLoginActivity.b = 4;
            throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
        }

    }

    /**
     *
     * Valide le login d'utilisateur saisi.
     */
    private void validLogin(String login) throws Exception {
        if (login != null ) {
            if (login.isEmpty()) {
                CreateLoginActivity.b = 5;
                throw new Exception();
            }
            if (login.trim().length() < 3) {
                CreateLoginActivity.b = 1;
                throw new Exception();
            }

        }
    }
}


