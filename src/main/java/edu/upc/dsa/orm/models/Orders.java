package edu.upc.dsa.orm.models;

public class Orders {

    private int userID;
    private int itemID;
    private int quantity;

    public Orders() {

    }

    public Orders(int id_game, int id_item, int quantity) {
        this.userID = id_game;
        this.itemID = id_item;
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
