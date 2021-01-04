package edu.upc.dsa.orm.models;


public class Item {                         //classe per objectes DINS del joc

    private int id; //Asignamos un id al name creado
    private String name;
    private int hit;
    private int defense;
    private int healing;
    private int damage;

    public Item() {

    }

    public Item(String name, int hit, int defense, int healing, int damage) {

        this();
        setName(name);
        setHit(hit);
        setDefense(defense);
        setHealing(healing);
        setDamage(damage);

        /*this.name = name;
        this.id = id;
        this.hit = hit;
        this.defense = defense;
        this.healing = healing;
        this.damage = damage;

         */
    }

    public String getName() {        return name;    }
    public void setName(String item) {
        this.name = item;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
