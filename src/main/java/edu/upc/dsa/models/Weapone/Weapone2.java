package edu.upc.dsa.models.Weapone;

import edu.upc.dsa.util.RandomUtils;

public class Weapone2 extends Weapone{

    public Weapone2(int hit, int damage) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.damage = damage;
    }
}
