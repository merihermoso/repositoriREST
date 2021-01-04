package edu.upc.dsa.orm.dao.inventory;


import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;


import edu.upc.dsa.orm.models.Item;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class InventoryDAOImp implements InventoryDAO{

    private static InventoryDAO instance;

    protected List<Item> items; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(InventoryDAOImp.class);

    private InventoryDAOImp() { //Constructor
        this.items = new LinkedList<>();
    }

    public static InventoryDAO getInstance() {
        if (instance==null) instance = new InventoryDAOImp();
        return instance;
    }

    @Override
    public int size() {
        return 1;  //Arreglar
    }


    @Override
    public Item addItem(Item p) {

        Session session;

        try {

            session = FactorySession.openSession();
            session.AddItem(p);
            session.close();

        } finally {

        }

        return p;
    }


    @Override
    public Item getItem(String id) {
        return null;
    }

    @Override
    public void deleteItem(String id) {

    }

    @Override
    public List<Item> findAll() {
        return null;
    }
}
