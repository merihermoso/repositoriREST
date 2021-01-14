package edu.upc.dsa.orm.models;

public class Inventory {

    private int id_user;
    private int id_item;
    private int quantity;

    public Inventory() {

    }

    public Inventory(int id_user, int id_item, int quantity, int quantityShop) {

        this();
        setId_user(id_user);
        setId_item(id_item);
        setQuantity(quantity);

    }

    public int getId_user() {       return id_user;    }
    public void setId_user(int id_user) {        this.id_user = id_user;    }

    public int getId_item() {        return id_item;    }
    public void setId_item(int id_item) {        this.id_item = id_item;    }

    public int getQuantity() {        return quantity;    }
    public void setQuantity(int quantity) {        this.quantity = quantity;    }

}
