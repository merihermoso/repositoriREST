package edu.upc.dsa.orm.models.Enemy;

import edu.upc.dsa.orm.util.RandomUtils;

public class Enemy1 extends Enemy{

    public Enemy1(){
        this.id = RandomUtils.getId();
        this.health = 100;
        this.damage = 5;
        this.speed = 10;
    }
    public Enemy1(int posX, int posY) {
        this();
        this.posX = posX;
        this.posY = posY;
    }
}