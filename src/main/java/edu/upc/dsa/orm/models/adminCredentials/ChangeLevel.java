package edu.upc.dsa.orm.models.adminCredentials;

public class ChangeLevel {

    private String username;
    private String password;
    private int newLevel;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getNewLevel() {
        return newLevel;
    }
    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }

}
