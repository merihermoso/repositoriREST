package edu.upc.dsa.orm.dao.item;

import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.User;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    public Item getItemById(int itemID) throws SQLException;

    public List<Item> findAll();




}
