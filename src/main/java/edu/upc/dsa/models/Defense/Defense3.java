package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defense3 extends Defense{

    public Defense3() {
        this.id = RandomUtils.getId();
        this.hit = 3;
        this.defense = 3;
    }
}
