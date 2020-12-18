package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defense3 extends Defense{

    public Defense3(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.defense = force;
    }
}
