package edu.upc.dsa.models.Weapone;

import edu.upc.dsa.util.RandomUtils;

public class Weapone3 extends Weapone{

    public Weapone3(int hit, int damage) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.damage = damage;
    }
}
