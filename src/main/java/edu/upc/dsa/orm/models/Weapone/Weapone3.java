package edu.upc.dsa.orm.models.Weapone;


import edu.upc.dsa.orm.util.RandomUtils;

public class Weapone3 extends Weapone{

    public Weapone3() {
        this.id = RandomUtils.getId();
        this.hit = 3;
        this.damage = 50;
    }
}
