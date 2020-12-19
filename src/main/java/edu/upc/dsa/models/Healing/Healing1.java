package edu.upc.dsa.models.Healing;

import edu.upc.dsa.util.RandomUtils;

public class Healing1 extends Healing {

    public Healing1() {
        this.id = RandomUtils.getId();
        this.hit = 2;
        this.healing = 5;
    }
}
