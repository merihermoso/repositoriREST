package edu.upc.dsa.models.Defense;

import edu.upc.dsa.util.RandomUtils;

public class Defence2 extends Defense{

    public Defence2(int hit, int force) {
        this.id = RandomUtils.getId();
        this.hit = hit;
        this.defense = force;
    }
}
