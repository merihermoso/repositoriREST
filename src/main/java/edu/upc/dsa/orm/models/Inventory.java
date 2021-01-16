package edu.upc.dsa.orm.models;

public class Inventory {

    private int id_game;
    private int id_item;
    private int quantity;

    public Inventory() {

    }

    public Inventory(int id_game, int id_item, int quantity) {
        this.id_game = id_game;
        this.id_item = id_item;
        this.quantity = quantity;
    }

    public int getId_game() {
        return id_game;
    }

    public void setId_game(int id_game) {
        this.id_game = id_game;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
