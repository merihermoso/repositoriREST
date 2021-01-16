package edu.upc.dsa.orm.models;

public class Inventory {

    private int gameID;
    private int itemID;
    private int quantity;

    public Inventory() {

    }

    public Inventory(int id_game, int id_item, int quantity) {
        this.gameID = id_game;
        this.itemID = id_item;
        this.quantity = quantity;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
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
