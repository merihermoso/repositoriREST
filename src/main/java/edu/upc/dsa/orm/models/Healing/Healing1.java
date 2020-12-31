package edu.upc.dsa.orm.models.Healing;


import edu.upc.dsa.orm.util.RandomUtils;

public class Healing1 extends Healing {

    public Healing1() {
        this.id = RandomUtils.getId();
        this.hit = 1;
        this.healing = 5;
    }
}
