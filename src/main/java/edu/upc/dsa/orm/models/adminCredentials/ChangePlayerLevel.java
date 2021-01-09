package edu.upc.dsa.orm.models.adminCredentials;

public class ChangePlayerLevel {

    private String username;
    private int newLevel;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getNewLevel() {
        return newLevel;
    }
    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }

}
