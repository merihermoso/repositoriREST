package edu.upc.dsa.orm.models;

public class Orders {

    private int id;
    private int id_user;
    private int id_item;
    private String orderDate;
    private String orderTime;

    public Orders() {

    }

    public Orders(int id, int id_user, int id_item, int quantity, String orderDate, String orderTime) {
        this.id = id;
        this.id_user = id_user;
        this.id_item = id_item;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

}
