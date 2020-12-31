package edu.upc.dsa.orm.models.Weapone;


import edu.upc.dsa.orm.util.RandomUtils;

public class Weapone1 extends Weapone{

    public Weapone1() {
        this.id = RandomUtils.getId();
        this.hit = 1;
        this.damage = 50;
    }
}
