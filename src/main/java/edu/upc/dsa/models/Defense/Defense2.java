package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defense2 extends Defense{

    public Defense2(int hit, int defence) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.defense = defence;
    }
}
