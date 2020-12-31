package edu.upc.dsa.orm.models.Healing;


import edu.upc.dsa.orm.models.Item;

public abstract class Healing extends Item {

    String objeto=  "Healing";
    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int healing; //Asignamos lo util que sera el objeto

    @Override
    public String getObjeto() {
        return objeto;
    }

    @Override
    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

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
