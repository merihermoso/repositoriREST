package edu.upc.dsa.orm.models.API;

public class GameSettings {

    private int id;
    private int min_health;
    private int max_health;
    private int max_damage;
    private int max_defense;
    private int max_healing;
    private int max_speed;

    public GameSettings() {


    }

    public GameSettings(int id, int min_health, int max_health, int max_damage, int max_defense, int max_healing
            , int max_speed) {

        this.id = id;
        this.min_health = min_health;
        this.max_health = max_health;
        this.max_damage = max_damage;
        this.max_defense = max_defense;
        this.max_healing = max_healing;
        this.max_speed = max_speed;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMin_health() {
        return min_health;
    }

    public void setMin_health(int min_health) {
        this.min_health = min_health;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public int getMax_damage() {
        return max_damage;
    }

    public void setMax_damage(int max_damage) {
        this.max_damage = max_damage;
    }

    public int getMax_defense() {
        return max_defense;
    }

    public void setMax_defense(int max_defense) {
        this.max_defense = max_defense;
    }

    public int getMax_healing() {
        return max_healing;
    }

    public void setMax_healing(int max_healing) {
        this.max_healing = max_healing;
    }

    public int getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }
}
