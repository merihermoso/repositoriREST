package edu.upc.dsa.orm.models;

public class Inventory {                         //classe per objectes DINS del joc

    private int id_user;
    private int id_item; //Asignamos un id al name creado
    private int quantity;

    public Inventory() {

    }

    public Inventory(int id_user, int id_item, int quantity) {

        this();
        setIdUser(id_user);
        setIdItem(id_item);
        setQuantity(quantity);                //aumentar o disminuir quan comprem/trobem un objecte
    }

    public int getIdUser() {       return id_user;    }
    public void setIdUser(int id_user) {        this.id_user = id_user;    }

    public int getIdItem() {        return id_item;    }
    public void setIdItem(int id_item) {        this.id_item = id_item;    }

    public int getQuantity() {        return quantity;    }
    public void setQuantity(int quantity) {        this.quantity = quantity;    }

    @Override
    public String toString(){
        return "Inventory{ id_user: "+id_user+",id_item: " +id_item+"quantity: "+quantity+"}";
    }
}
