package edu.upc.dsa.orm.models.API;

public class RankingPositionResponse {

    private int position;

    public RankingPositionResponse() {

    }

    public RankingPositionResponse(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
