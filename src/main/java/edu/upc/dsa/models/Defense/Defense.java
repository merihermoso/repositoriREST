package edu.upc.dsa.models.Defense;


import edu.upc.dsa.models.Item;

public abstract class Defense extends Item {

    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int defense; //Asignamos la cantidad de daño que protege


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
}
