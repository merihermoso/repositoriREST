package edu.upc.dsa.orm.models.API;

public class IdResponse {

    private int id;

    public IdResponse() {

    }

    public IdResponse(int userID) {
        this.id = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int userID) {
        this.id = userID;
    }
}
