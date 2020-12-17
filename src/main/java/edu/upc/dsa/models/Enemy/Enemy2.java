package edu.upc.dsa.models.Enemy;

import edu.upc.dsa.util.RandomUtils;

public class Enemy2 extends Enemy {
    String id; //Enemy's id
    int posX; //Enemy's "X" position
    int posY; //Enemy's "Y" position
    int health; //Enemy's health
    int damage; //Enemy's damage per hit
    int speed; //Enemy's speedd

    public Enemy2() {
        this.id = RandomUtils.getId();
        //Ejemplo
        this.health = 100;
        this.damage = 5;
        this.speed = 10;
    }

    public Enemy2(int posX, int posY) {
        this();
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
