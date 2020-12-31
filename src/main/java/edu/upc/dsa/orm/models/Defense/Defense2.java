package edu.upc.dsa.orm.models.Defense;

import edu.upc.dsa.orm.util.RandomUtils;

public class Defense2 extends Defense{

    public Defense2() {
        this.id = RandomUtils.getId();
        this.hit = 2;
        this.defense = 2;
    }
}
