package edu.upc.dsa.models.Defense;


import edu.upc.dsa.util.RandomUtils;

public class Defense1 extends Defense{

    public Defense1() {
        this.id = RandomUtils.getId();
        this.hit = 10;
        this.defense = 2;
    }
}
