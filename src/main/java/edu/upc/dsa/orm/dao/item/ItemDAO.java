package edu.upc.dsa.orm.dao.item;

import edu.upc.dsa.orm.exeptions.UserNotFoundException;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.Inventory;
import edu.upc.dsa.orm.models.Item;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ItemDAO {

    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Item> findAll();
    public int size();
    public int updateItem(Item item)throws SQLException;

    /*****************************************  OBTENIM ITEM inventari *************************************************/
    public Item getItemById(int itemID) throws SQLException;
    public Item getItemByName(String name) throws SQLException;

 //   public Item getItemByUsername(String username) throws SQLException; //només retorna el primer i no va bé ARREGLAR
    public HashMap<Integer, Inventory> getItemsUser(String username) throws UserNotFoundException;

    int getPriceItem(int id_item) throws SQLException;  //Falta la part de SERVEI


    int getIdByName(String name) throws SQLException;
    /*****************************************  REGISTRE ITEM     *************************************************/
    boolean registerItem(ItemCredentials itemCredentials) throws SQLException, IllegalAccessException;


}
