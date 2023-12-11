package edu.praktikum.models;

public class CourierCreate {

    private String login;
    private String password;
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public CourierCreate withLogin(String login) {
        this.login = login;
        return this;
    }
    public CourierCreate withPassword(String password) {
        this.password = password;
        return this;
    }
    public CourierCreate withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

}