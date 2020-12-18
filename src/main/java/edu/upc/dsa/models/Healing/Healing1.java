package edu.upc.dsa.models.Healing;

import edu.upc.dsa.util.RandomUtils;

public class Healing1 extends Healing {

    public Healing1(int hit, int healing) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.healing = healing;
    }
}
