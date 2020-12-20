package edu.upc.dsa.models.Healing;


import edu.upc.dsa.util.RandomUtils;

public class Healing3 extends Healing {

    public Healing3() {
        this.id = RandomUtils.getId();
        this.hit = 2;
        this.healing = 5;
    }
}
