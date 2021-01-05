package edu.upc.dsa.orm.dao.item;

import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    public Item getItemById(int itemID) throws SQLException;
    public Item getItemByName(String name) throws SQLException;

    public List<Item> findAll();

    boolean registerItem(ItemCredentials itemCredentials) throws SQLException;




}
