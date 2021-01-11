package edu.upc.dsa.orm.dao.inventory;

import edu.upc.dsa.orm.models.Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO {

    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Inventory> findAll();
    public int size();
    public int updateInventory(Inventory inventory)throws SQLException;
  //  public int updateInventory(Item item)throws SQLException;     s'ha de fer per parametre!!!

    /*****************************************  OBTENIM ITEM inventari *************************************************/
  //  public Inventory getItemByIdUser(int itemID) throws SQLException;
  //  public Inventory getItemByIdItem(int itemID) throws SQLException;



}
