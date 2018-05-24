package com.ldnr.recettes.Beans;

public class User {

    private int id_user;
    private String login;
    private String email;
    private String password;

    public User(int id_user, String login, String email, String password) {
        this.id_user = id_user;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public void setId(int id) {
        this.id_user = id_user;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {

        return id_user;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
