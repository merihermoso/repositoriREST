package edu.upc.dsa.orm.models;


public class Inventory {                         //classe per objectes DINS del joc

    private int id_player;
    private int id_item; //Asignamos un id al name creado
    private int quantity;

    public Inventory() {

    }

    public Inventory(int idPlayer, int idItem, int quantity) {

        this();
        setIdPlayer(idPlayer);
        setIdItem(idItem);
        setQuantity(quantity);                //aumentar o disminuir quan comprem/trobem un objecte
    }

    public int getIdPlayer() {       return id_player;    }
    public void setIdPlayer(int idPlayer) {        this.id_player = id_player;    }

    public int getIdItem() {        return id_item;    }
    public void setIdItem(int id_item) {        this.id_item = id_item;    }

    public int getQuantity() {        return quantity;    }
    public void setQuantity(int quantity) {        this.quantity = quantity;    }

    @Override
    public String toString(){
        return "Inventory { id_player"+id_player+",id_item :" +id_item+"quantity :"+quantity+"}";
    }
}
