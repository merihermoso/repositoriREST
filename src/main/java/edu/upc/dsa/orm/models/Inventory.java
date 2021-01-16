package edu.upc.dsa.orm.models;

public class Inventory {



    private int userID;
    private int itemID;
    private int quantity;
    private int quantityShop;


    public Inventory() {

    }

    public Inventory(int userID, int itemID, int quantity, int quantityShop) {

        this();
        setUserID(userID);
        setItemID(itemID);
        setQuantity(quantity);
        setQuantityShop(quantityShop);

    }

    public int getUserID() {        return userID;    }
    public void setUserID(int userID) {        this.userID = userID;    }

    public int getItemID() {        return itemID;    }
    public void setItemID(int itemID) {        this.itemID = itemID;    }

    public int getQuantity() {        return quantity;    }
    public void setQuantity(int quantity) {        this.quantity = quantity;    }

    public int getQuantityShop() {        return quantityShop;    }
    public void setQuantityShop(int quantityShop) {        this.quantityShop = quantityShop;    }

}
