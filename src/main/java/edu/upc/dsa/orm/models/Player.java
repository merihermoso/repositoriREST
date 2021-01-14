package edu.upc.dsa.orm.models;

public class Player {

    private int id;
    private String name;
    private String status;
    private int coins;
    private int score;
    private int level;
    private int speed;
    private int hit;
    private int defense;
    private int healing;
    private int damage;


    public Player() {
    }
    public Player(String status, int coins, int score, int level, int speed, int hit, int defense, int healing, int damage) {
        setStatus(status);
        setCoins(coins);
        setScore(score);
        setLevel(level);
        setSpeed(speed);
        setHit(hit);
        setDefense(defense);
        setHealing(healing);
        setDamage(damage);
    }
    public int getId() {
        return this.id;
    }
    public void setId(int elementID) {
        this.id=elementID;
    }

    public String getStatus() {        return status;    }
    public void setStatus(String status) {        this.status = status;    }

    public int getCoins() {        return coins;    }
    public void setCoins(int coins) {        this.coins = coins;    }

    public int getScore() {        return score;    }
    public void setScore(int score) {        this.score = score;    }

    public int getLevel() {        return level;    }
    public void setLevel(int level) {        this.level = level;    }

    public int getSpeed() {        return speed;    }
    public void setSpeed(int speed) {        this.speed = speed;    }

    public int getHit() {        return hit;    }
    public void setHit(int hit) {        this.hit = hit;    }

    public int getDefense() {        return defense;    }
    public void setDefense(int defense) {        this.defense = defense;    }

    public int getHealing() {        return healing;    }
    public void setHealing(int healing) {        this.healing = healing;    }

    public int getDamage() {        return damage;    }
    public void setDamage(int damage) {        this.damage = damage;    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}