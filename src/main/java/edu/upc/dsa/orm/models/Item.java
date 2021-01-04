package edu.upc.dsa.orm.models;


public class Item {

    private int id; //Asignamos un id al objeto creado
    private String objeto;
    private int hit;
    private int defense;
    private int healing;
    private int damage;

    public Item() {

    }

    public Item(String objeto, int hit, int defense, int healing, int damage) {

        this();
        setId(0);
        setObjeto(objeto);
        setHit(hit);
        setDefense(defense);
        setHealing(healing);
        setDamage(damage);

        /*this.objeto = objeto;
        this.id = id;
        this.hit = hit;
        this.defense = defense;
        this.healing = healing;
        this.damage = damage;

         */
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String item) {
        this.objeto = item;
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
