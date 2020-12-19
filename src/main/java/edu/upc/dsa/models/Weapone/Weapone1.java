package edu.upc.dsa.models.Weapone;

import edu.upc.dsa.util.RandomUtils;

public class Weapone1 extends Weapone{

    public Weapone1() {
        this.id = RandomUtils.getId();
        this.hit = 1;
        this.damage = 50;
    }
}
