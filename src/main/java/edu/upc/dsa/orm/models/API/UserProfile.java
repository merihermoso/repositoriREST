package edu.upc.dsa.orm.models.API;

public class UserProfile {

    private String username;
    private String email;
    private String birthdate;
    private int score;
    private int level;
    private int ranking_position;

    public UserProfile() {

    }

    public UserProfile(String username, String email, String birthdate, int score, int level, int ranking_position) {
        this.username = username;
        this.email = email;
        this.birthdate = birthdate;
        this.score = score;
        this.level = level;
        this.ranking_position = ranking_position;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getRanking_position() {
        return ranking_position;
    }

    public void setRanking_position(int ranking_position) {
        this.ranking_position = ranking_position;
    }
}
