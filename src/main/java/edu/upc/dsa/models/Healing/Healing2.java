package edu.upc.dsa.models.Healing;

import edu.upc.dsa.util.RandomUtils;

public class Healing2 extends Healing {

    public Healing2(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.force = force;
    }
}
