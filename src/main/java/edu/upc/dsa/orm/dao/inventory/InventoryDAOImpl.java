package edu.upc.dsa.orm.dao.inventory;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.*;

import java.sql.SQLException;
import java.util.*;

public class InventoryDAOImpl implements InventoryDAO {
    private static InventoryDAO instance;

    private InventoryDAOImpl() {
    }

    public static InventoryDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new InventoryDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Inventory> findAll(){

        Session session;
        List<Inventory> inventoryList;

        HashMap<Integer, Inventory> result;

        session = FactorySession.openSession();
        result = session.findAll(Inventory.class);

        inventoryList = new ArrayList<>(result.values());

        session.close();

        return inventoryList;
    }

    public int size() {
        Session session;
        HashMap<String, Inventory> inventory = null;
        try{
            session = FactorySession.openSession();
            inventory = session.findAll(Inventory.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventory.size();
    }


    public int updateInventory(Inventory inventory) throws SQLException {

        Session session = null;
        int res=1;

        try {
            session = FactorySession.openSession();
            session.updateInventory(inventory);
            res =0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            return res;
        }
    }


}
