package edu.upc.dsa.models.Weapone;

import edu.upc.dsa.util.RandomUtils;

public class Weapone3 extends Weapone{

    public Weapone3() {
        this.id = RandomUtils.getId();
        this.hit = 1;
        this.damage = 50;
    }
}
