package edu.upc.dsa.models.Healing;

import edu.upc.dsa.util.RandomUtils;

public class Healing1 extends Healing {

    public Healing1(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.force = force;
    }
}
