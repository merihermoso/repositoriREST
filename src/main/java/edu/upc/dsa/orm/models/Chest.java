package edu.upc.dsa.orm.models;

import edu.upc.dsa.orm.util.RandomUtils;

public class Chest {

    String id; //Enemy's id
    int posX; //Enemy's "X" position
    int posY; //Enemy's "Y" position

    public Chest(int posX, int posY) {
        this.id = RandomUtils.getId();
        this.posX = posX;
        this.posY = posY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
