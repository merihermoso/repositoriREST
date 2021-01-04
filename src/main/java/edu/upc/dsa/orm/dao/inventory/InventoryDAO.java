package edu.upc.dsa.orm.dao.inventory;


import edu.upc.dsa.orm.models.Item;


import java.util.List;

public interface InventoryDAO {

    int size();

    public Item addItem(Item p);

    Item getItem(String id);
    void deleteItem(String id);
    List<Item> findAll(); //Recuperar todos los objetos Item
}
