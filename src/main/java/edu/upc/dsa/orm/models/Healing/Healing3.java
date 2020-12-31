package edu.upc.dsa.orm.models.Healing;


import edu.upc.dsa.orm.util.RandomUtils;

public class Healing3 extends Healing {

    public Healing3() {
        this.id = RandomUtils.getId();
        this.hit = 3;
        this.healing = 5;
    }
}
