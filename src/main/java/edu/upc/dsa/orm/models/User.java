package edu.upc.dsa.orm.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String birthdate;
    private int score;
    private int level;

 //   private int creditCard;

    public User() {

    }

    public User(String username, String email, String password, String birthdate){      //int creditCard

        this();
        setId(0);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBirthdate(birthdate);
        setScore(0);
        setLevel(1);
      //  setCreditCard(creditCard);

    }

    public int getId() {
        return this.id;
    }
    public void setId(int userID) {
        this.id=userID;
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

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
/*
    public int getCreditCard() {        return creditCard;    }
    public void setCreditCard(int creditCard) {        this.creditCard = creditCard;    }*/

    @Override
    public String toString(){
        return "User { id: "+id+",Username:" +username+", Email:"+email+", Password:"+password+", Birthdate:"+birthdate+ ", Score:"+score+", Level:"+level+"}";
    }
}
