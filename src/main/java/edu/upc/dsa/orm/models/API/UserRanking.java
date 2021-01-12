package edu.upc.dsa.orm.models.API;

public class UserRanking {

    private int position;

    public UserRanking() {

    }

    public UserRanking(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
