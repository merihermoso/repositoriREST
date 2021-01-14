package edu.upc.dsa.orm.models.API;

public class RegisterCredentials {

    private String username;
    private String password;
    private String email;
    private int birthdate_year;
    private int birthdate_month;
    private int birthdate_day;

    public RegisterCredentials(){

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getBirthdate_year() {
        return birthdate_year;
    }

    public void setBirthdate_year(int birthdate_year) {
        this.birthdate_year = birthdate_year;
    }

    public int getBirthdate_month() {
        return birthdate_month;
    }

    public void setBirthdate_month(int birthdate_month) {
        this.birthdate_month = birthdate_month;
    }

    public int getBirthdate_day() {
        return birthdate_day;
    }

    public void setBirthdate_day(int birthdate_day) {
        this.birthdate_day = birthdate_day;
    }
}
