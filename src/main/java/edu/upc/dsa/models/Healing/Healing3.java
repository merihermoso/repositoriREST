package edu.upc.dsa.models.Healing;

import edu.upc.dsa.util.RandomUtils;

public class Healing3 extends Healing {

    public Healing3(int hit, int healing) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.healing = healing;
    }
}
