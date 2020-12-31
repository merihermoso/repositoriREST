package edu.upc.dsa.orm.models.Defense;


import edu.upc.dsa.orm.util.RandomUtils;

public class Defense1 extends Defense{

    public Defense1() {
        this.id = RandomUtils.getId();
        this.hit = 1;
        this.defense = 2;
    }
}
