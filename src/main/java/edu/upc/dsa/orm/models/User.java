package edu.upc.dsa.orm.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String birthdate;
    private int score;
    private int level;

    private int coins;
    private int speed;
    private int hit;
    private int defense;
    private int healing;
    private int damage;

    public User() {

    }

    public User(String username, String email, String password, String birthdate){

        setId(0);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBirthdate(birthdate);
        setScore(0);
        setLevel(1);

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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
