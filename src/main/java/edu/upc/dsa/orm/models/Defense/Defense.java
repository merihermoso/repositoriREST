package edu.upc.dsa.orm.models.Defense;


import edu.upc.dsa.orm.models.Item;

public abstract class Defense extends Item {

    String objeto =  "Defense";
    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int defense; //Asignamos la cantidad de daño que protege

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

    @Override
    public int getHit() {
        return hit;
    }

    @Override
    public void setHit(int hit) {
        this.hit = hit;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }
}