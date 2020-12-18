package edu.upc.dsa.models.Enemy;

import edu.upc.dsa.util.RandomUtils;

public class Enemy3 extends Enemy {

    public Enemy3() {
        this.id = RandomUtils.getId();
        this.health = 100;
        this.damage = 5;
        this.speed = 10;
    }

    public Enemy3(int posX, int posY) {
        this();
        this.posX = posX;
        this.posY = posY;
    }

}

