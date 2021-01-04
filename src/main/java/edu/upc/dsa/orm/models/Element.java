package edu.upc.dsa.orm.models;

public class Element {              //classe per objectes de la botiga

    private int id;
    private String name;
    private String description;
    private int price;

    public Element() {

    }
    public Element(String name, String description, int price) {

        setName(name);
        setDescription(description);
        setPrice(price);

    }
    public int getId() {
        return this.id;
    }
    public void setId(int elementID) {
        this.id=elementID;
    }

    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }

    public String getDescription() {        return description;    }
    public void setDescription(String description) {        this.description = description;    }

    public int getPrice() {        return price;    }
    public void setPrice(int price) {        this.price = price;    }

}