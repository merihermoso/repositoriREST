package edu.upc.dsa.orm.dao.item;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Item;

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


}
