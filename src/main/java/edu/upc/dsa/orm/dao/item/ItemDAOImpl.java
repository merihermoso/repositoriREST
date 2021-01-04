package edu.upc.dsa.orm.dao.item;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static ItemDAO instance;

    private ItemDAOImpl() {
    }

    public static ItemDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new ItemDAOImpl();
        return instance;
    }

    public List<Item> findAll(){

        Session session;
        List<Item> itemsList;

        HashMap<Integer, Item> result;

        session = FactorySession.openSession();
        result = session.findAll(Item.class);

        itemsList = new ArrayList<>(result.values());

        session.close();

        return itemsList;
    }

    public Item getItemById(int itemID) throws SQLException {
        Session session = null;
        Item item = new Item();
        try {
            session = FactorySession.openSession();
            item = (Item) session.getById(item, itemID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return item;
    }


}
