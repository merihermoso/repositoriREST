package edu.upc.dsa.orm.models.Enemy;

import edu.upc.dsa.orm.util.RandomUtils;

public class Enemy2 extends Enemy {

    public Enemy2() {
        this.id = RandomUtils.getId();
        this.health = 100;
        this.damage = 5;
        this.speed = 10;
    }

    public Enemy2(int posX, int posY) {
        this();
        this.posX = posX;
        this.posY = posY;
    }
}
