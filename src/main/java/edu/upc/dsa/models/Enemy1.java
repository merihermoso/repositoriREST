package edu.upc.dsa.models;

public class Enemy1 {
    String id; //Enemy's id
    int posX; //Enemy's "X" position
    int posY; //Enemy's "Y" position
    int health; //Enemy's health
    int damage; //Enemy's damage per hit
    int speed; //Enemy's speed

    public Enemy1(String id, int posX, int posY, int health, int damage, int speed) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.damage = damage;
        this.speed = speed;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
