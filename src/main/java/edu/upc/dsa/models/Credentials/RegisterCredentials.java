package edu.upc.dsa.models.Credentials;

public class RegisterCredentials {

    String username;
    String email;
    String password;
    String confirm;
    String birthdate;

    public RegisterCredentials(String username, String email, String password, String confirm, String birthdate) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.birthdate = birthdate;
    }

    public RegisterCredentials() {}

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

    public String getConfirm() { return confirm; }

    public void setConfirm(String confirm) { this.confirm = confirm; }

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

    @Override
    public String toString() {
        return "RegisterCredentials{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirm='" + confirm + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
