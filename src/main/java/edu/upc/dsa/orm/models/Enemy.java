package edu.upc.dsa.orm.models;


public class Enemy {                         //classe per objectes DINS del joc

    private int id; //Asignamos un id al name creado
    private String name;
    private int max_health;
    private int reward;
    private int damage;

    public Enemy() {

    }

    public Enemy(int id, String name, int max_health, int reward, int damage) {
        this.id = id;
        this.name = name;
        this.max_health = max_health;
        this.reward = reward;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
