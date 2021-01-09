package edu.upc.dsa.orm.models.adminCredentials;

public class ChangePlayerStatus {          //per l'usuari s'hauria de modificar quan el PlayerScore supera el valor que hi havia abans aquí

    private String username;
    private String newStatus;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewStatus() {
        return newStatus;
    }
    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

}
