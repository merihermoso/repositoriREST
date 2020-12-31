package edu.upc.dsa.orm.models.Weapone;


import edu.upc.dsa.orm.util.RandomUtils;

public class Weapone2 extends Weapone{

    public Weapone2() {
        this.id = RandomUtils.getId();
        this.hit = 2;
        this.damage = 50;
    }
}
