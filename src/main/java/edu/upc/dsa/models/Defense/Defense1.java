package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defense1 extends Defense{

    public Defense1(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.defense = force;
    }
}
