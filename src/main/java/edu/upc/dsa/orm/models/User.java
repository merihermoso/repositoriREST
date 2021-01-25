package edu.upc.dsa.orm.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String birthdate;
    private String profile_photo;
    private int score;

    public User() {

    }

    public User(String username, String email, String password, String birthdate){

        setId(0);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBirthdate(birthdate);
        setScore(0);
        setProfile_photo("/Media/profile.png");

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }
}
