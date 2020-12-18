package edu.upc.dsa.models.Defense;


public abstract class Defense {

    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int defense; //Asignamos la cantidad de daño que protege

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

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
