package edu.upc.dsa.orm.models.API;

public class UserRanking {

    private String username;
    private int score;
    private int position;

    public UserRanking() {


    }

    public UserRanking(String username, int score, int position) {

        this.username = username;
        this.score = score;
        this.position = position;

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
