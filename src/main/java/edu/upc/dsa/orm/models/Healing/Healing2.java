package edu.upc.dsa.orm.models.Healing;


import edu.upc.dsa.orm.util.RandomUtils;

public class Healing2 extends Healing {

    public Healing2() {
        this.id = RandomUtils.getId();
        this.hit = 2;
        this.healing = 5;
    }
}
