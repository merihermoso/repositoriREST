package edu.upc.dsa.models.Healing;

import edu.upc.dsa.util.RandomUtils;

public class Healing3 extends Healing {

    public Healing3(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.force = force;
    }
}
