package edu.upc.dsa.orm.models;

public class Orders {

    private int id_user;
    private int id_item;
    private int quantity;

    public Orders() {

    }

    public Orders(int id_user, int id_item, int quantity) {
        this.id_user = id_user;
        this.id_item = id_item;
        this.quantity = quantity;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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
