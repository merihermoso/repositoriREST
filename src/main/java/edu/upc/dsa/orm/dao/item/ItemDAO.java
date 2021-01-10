package edu.upc.dsa.orm.dao.item;

import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Item> findAll();
    public int size();

    /*****************************************  OBTENIM ITEM inventari *************************************************/
    public Item getItemById(int itemID) throws SQLException;
    public Item getItemByName(String name) throws SQLException;

    public Item getItemByUsername(String username) throws SQLException;

    int getIdByName(String name) throws SQLException;
    /*****************************************  REGISTRE ITEM     *************************************************/
    boolean registerItem(ItemCredentials itemCredentials) throws SQLException;




}
