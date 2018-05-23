package com.ldnr.recettes.TestForm;


import android.content.res.Resources;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.ldnr.recettes.Activities.CreateLoginActivity;
import com.ldnr.recettes.Beans.User;
import com.ldnr.recettes.R;

public class ConfirmLoginForm {

    public User ConfirmNewLoginForm(String login, String email, String password, String confirm_password) {

        User addUser = new User();

        try {
            validLogin(login, addUser);
        } catch (Exception e) {

        }

        try {
            validEmail(email, addUser);
        } catch (Exception e) {


        }

        try {
            validPassword(password, confirm_password, addUser);
        } catch (Exception e) {

        }

        return addUser;
    }


    /**
     * Valide l'adresse mail saisie.
     */
    private void validEmail(String email, User addUser) throws Exception {

        if (!email.isEmpty() && email.trim().length() != 0) {
                if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception(" non valide.");

            }
        } else if (email.isEmpty()) {
            throw new Exception("Merci de saisir une adresse mail.");

        }
        else addUser.setEmail(email);

    }

    /**
     * Valide les passwword saisis.
     */
    public static int validPassword(String password, String confirm_password, User addUser) throws Exception {
        int i;
        //Log.d("whath the fuck",password);
        /*if (!(password.isEmpty() && password.trim().length() == 0 && confirm_password.isEmpty() && confirm_password.trim().length() == 0)) {
            if (!password.equals(confirm_password)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            }else if (password.trim().length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else if (password.isEmpty() ) {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }*/
        if(password.isEmpty() || confirm_password.isEmpty())
           // throw new Exception("Merci de saisir et confirmer votre mot de passe.");
            return 5;
        else if(!password.equals(confirm_password))
            throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
        CreateLoginActivity.onErrorCatch(4);
        else if(password.trim().length() < 3)
            throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
        else  addUser.setPassword(password);
    }

    /**
     *
     * Valide le login d'utilisateur saisi.
     */
    private void validLogin(String login, User addUser) throws Exception {
        if (login != null && login.trim().length() < 3) {
            throw new Exception("3 caractères minimum.");
        }
        else addUser.setLogin(login);
    }
}


