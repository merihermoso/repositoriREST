package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class User {

    String id_user;
    String username;
    String email;
    String birthdate;
    String pwd;
    int score;

    public User() {
        this.id_user = RandomUtils.getId();
        this.score = 0;
    }

    public User(String username, String pwd, String email, String birthdate) {
        this();
        this.setPwd(pwd);
        this.setUsername(username);
        this.setEmail(email);
        this.setBirthdate(birthdate);
    }

    public String getId() {
        return this.id_user;
    }

    public void setId(String id) {
        this.id_user =id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public String getBirthdate() { return this.birthdate; }

    public String getEmail() { return this.email; }

    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User [id_user = "+ id_user +", username = " + username + ", pwd = " + pwd +"]";
    }

}