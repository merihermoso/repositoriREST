package edu.upc.dsa.models.Healing;

import edu.upc.dsa.models.Item;

public abstract class Healing extends Item {

    //Cuando cogemos un objeto debemos añadirlo a la lista de objects

    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int healing; //Asignamos lo util que sera el objeto


    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getHealing() {
        return healing;
    }

    public void setHealing(int healing) {
        this.healing = healing;
    }
}
