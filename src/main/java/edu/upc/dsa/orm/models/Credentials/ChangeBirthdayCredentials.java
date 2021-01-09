package edu.upc.dsa.orm.models.Credentials;

public class ChangeBirthdayCredentials {

    private String username;
    private String password;
    private String newBirthday;

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

    public String getNewBirthday() {
        return newBirthday;
    }
    public void setNewBirthday(String newBirthday) {
        this.newBirthday = newBirthday;
    }

}
