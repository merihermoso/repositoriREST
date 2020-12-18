package edu.upc.dsa.models.Healing;

public abstract class Healing {

    //Cuando cogemos un objeto debemos añadirlo a la lista de objects

    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int force; //Asignamos lo util que sera el objeto


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

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
}
