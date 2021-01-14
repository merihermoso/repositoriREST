package edu.upc.dsa.orm.models;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String birthdate;
    private String status;
    private int score;
    private int level;

    public User() {

    }

    public User(String username, String email, String password, String birthdate){      //int creditCard

        this();
        setId(0);
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setBirthdate(birthdate);
        setScore(0);
        setLevel(1);

    }

    public int getId() {
        return this.id;
    }
    public void setId(int userID) {
        this.id=userID;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", level=" + level +
                '}';
    }

}
