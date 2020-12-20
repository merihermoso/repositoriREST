package edu.upc.dsa.models.Weapone;


import edu.upc.dsa.models.Item;

public abstract class Weapone extends Item {

    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int damage; //Asignamos el daño que hace el arma


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
