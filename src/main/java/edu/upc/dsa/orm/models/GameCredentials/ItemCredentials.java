package edu.upc.dsa.orm.models.GameCredentials;

public class ItemCredentials {

    private int id;
    private String name;
    private int hit;
    private int defense;
    private int healing;
    private int damage;

    public String getName() {        return name;    }
    public void setName(String item) {
        this.name = item;
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
