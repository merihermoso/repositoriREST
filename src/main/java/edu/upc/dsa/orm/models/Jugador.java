package edu.upc.dsa.orm.models;

import edu.upc.dsa.orm.util.RandomUtils;

public class Jugador {

    String id_user;
    int health;
    int speed;
    int x;
    int y;

    //Constructor
    public Jugador(){
        this.id_user = RandomUtils.getId();
        this.health = 100;
        this.speed = 10;
    }
    public Jugador(int posX, int posY) {
        this();
        this.x = posX;
        this.y = posY;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
