package edu.upc.dsa.orm.models;

public class Inventory {

    private int id_user;
    private int id_item;
    private int quantity;
    private int quantityShop;


    public Inventory() {

    }

    public Inventory(int id_user, int id_item, int quantity, int quantityShop) {

        this();
        setId_user(id_user);
        setId_item(id_item);
        setQuantity(quantity);                //aumentar o disminuir quan comprem=1(botiga)/gastem=2(joc)
        setQuantityShop(quantityShop);
    }

    public int getId_user() {       return id_user;    }
    public void setId_user(int id_user) {        this.id_user = id_user;    }

    public int getId_item() {        return id_item;    }
    public void setId_item(int id_item) {        this.id_item = id_item;    }

    public int getQuantity() {        return quantity;    }
    public void setQuantity(int quantity) {        this.quantity = quantity;    }

    public int getQuantityShop() {        return quantityShop;    }
    public void setQuantityShop(int quantityShop) {        this.quantityShop = quantityShop;    }

    @Override
    public String toString(){
        return "Inventory{ id_user: "+id_user+",id_item: " +id_item+"quantity: "+quantity+"quantityShop:"+quantityShop+"}";
    }
}
