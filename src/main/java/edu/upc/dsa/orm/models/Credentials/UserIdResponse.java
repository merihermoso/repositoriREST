package edu.upc.dsa.orm.models.Credentials;

public class UserIdResponse {

    private int userID;

    public UserIdResponse() {

    }

    public UserIdResponse(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
