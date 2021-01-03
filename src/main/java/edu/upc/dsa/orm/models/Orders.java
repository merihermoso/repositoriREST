package edu.upc.dsa.orm.models;

public class Orders {

    private int id;
    private String date;
    private String time;
    private int price;

    public Orders() {

    }
    public Orders(String date, String time, int price) {
        this();
        setDate(date);
        setTime(time);
        setPrice(price);

    }
    public int getId() {
        return this.id;
    }
    public void setId(int orderID) {
        this.id=orderID;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }


}