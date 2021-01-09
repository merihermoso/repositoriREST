package edu.upc.dsa.orm.models.adminCredentials;

public class ChangePlayerScore {          //per l'usuari s'hauria de modificar quan el PlayerScore supera el valor que hi havia abans aquí

    private String username;
    private int newScore;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getNewScore() {
        return newScore;
    }
    public void setNewScore(int newScore) {
        this.newScore = newScore;
    }

}
