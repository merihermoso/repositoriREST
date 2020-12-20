package edu.upc.dsa.models.Weapone;


import edu.upc.dsa.util.RandomUtils;

public class Weapone2 extends Weapone{

    public Weapone2() {
        this.id = RandomUtils.getId();
        this.hit = 2;
        this.damage = 50;
    }
}
