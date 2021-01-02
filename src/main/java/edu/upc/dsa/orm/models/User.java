package edu.upc.dsa.orm.models;

public class User {

    private int userID;
    private String username;
    private String email;
    private String password;
    private int nivel;

    public User(){

    }

    public User(String username, String email, String password, int nivel){
        this.username = username;
        this.email = email;
        this.password = password;
        this.nivel = nivel;
    }

    public int getId() {
        return this.userID;
    }
    public void setId(int userID) {
        this.userID=userID;
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

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
