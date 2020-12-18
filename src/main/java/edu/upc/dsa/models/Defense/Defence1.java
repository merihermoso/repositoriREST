package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defence1 extends Defense{

    public Defence1(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.defense = force;
    }
}
