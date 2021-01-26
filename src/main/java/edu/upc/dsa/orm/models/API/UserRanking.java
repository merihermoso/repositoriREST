package edu.upc.dsa.orm.models.API;

public class UserRanking {

    private String username;
    private int score;
    private String profile_photo;
    private int position;

    public UserRanking() {


    }

    public UserRanking(String username, int score, String profile_photo, int position) {

        this.username = username;
        this.score = score;
        this.profile_photo = profile_photo;
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

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }
}
