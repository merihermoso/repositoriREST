package edu.upc.dsa.models.Defense;


import edu.upc.dsa.models.Item;

public abstract class Defense extends Item {

    String item =  "Defense";
    String id; //Asignamos un id al objeto creado
    int hit; //Asignamos un numero maximo de usos
    int defense; //Asignamos la cantidad de daño que protege

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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
