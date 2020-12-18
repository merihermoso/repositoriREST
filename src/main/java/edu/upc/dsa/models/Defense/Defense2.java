package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defense2 extends Defense{

    public Defense2() {
        this.id = RandomUtils.getId();
        this.hit = 10;
        this.defense = 2;
    }
}
