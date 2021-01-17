package edu.upc.dsa.orm.models.API;

public class GameSettings {

    private int id;
    private int username_min_length;
    private int username_max_length;
    private int password_min_length;
    private int password_max_length;
    private int email_min_length;
    private int email_max_length;
    private int min_age;

    public GameSettings() {


    }

    public GameSettings(int id, int username_min_length, int username_max_length
            , int password_min_length, int password_max_length, int email_min_length, int email_max_length
            , int min_age) {

        this.id = id;
        this.username_min_length = username_min_length;
        this.username_max_length = username_max_length;
        this.password_min_length = password_min_length;
        this.password_max_length = password_max_length;
        this.email_min_length = email_min_length;
        this.email_max_length = email_max_length;
        this.min_age = min_age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsername_min_length() {
        return username_min_length;
    }

    public void setUsername_min_length(int username_min_length) {
        this.username_min_length = username_min_length;
    }

    public int getUsername_max_length() {
        return username_max_length;
    }

    public void setUsername_max_length(int username_max_length) {
        this.username_max_length = username_max_length;
    }

    public int getPassword_min_length() {
        return password_min_length;
    }

    public void setPassword_min_length(int password_min_length) {
        this.password_min_length = password_min_length;
    }

    public int getPassword_max_length() {
        return password_max_length;
    }

    public void setPassword_max_length(int password_max_length) {
        this.password_max_length = password_max_length;
    }

    public int getEmail_min_length() {
        return email_min_length;
    }

    public void setEmail_min_length(int email_min_length) {
        this.email_min_length = email_min_length;
    }

    public int getEmail_max_length() {
        return email_max_length;
    }

    public void setEmail_max_length(int email_max_length) {
        this.email_max_length = email_max_length;
    }

    public int getMin_age() {
        return min_age;
    }

    public void setMin_age(int min_age) {
        this.min_age = min_age;
    }

}
