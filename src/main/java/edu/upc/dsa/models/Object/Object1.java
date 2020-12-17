package edu.upc.dsa.models.Object;

import edu.upc.dsa.util.RandomUtils;

public class Object1 extends Object {
    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int force; //Asignamos lo util que sera el objeto

    public Object1() {
        this.id = RandomUtils.getId();
    }

    public Object1(int hit, int force) {
        this();
        this.hit = hit;
        this.force = force;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    @Override
    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
}
