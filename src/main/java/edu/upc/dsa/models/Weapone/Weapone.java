package edu.upc.dsa.models.Weapone;


public abstract class Weapone {

    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int damage; //Asignamos el daño que hace el arma

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
