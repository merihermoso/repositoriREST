package edu.upc.dsa.orm.dao.inventory;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.*;

import java.sql.SQLException;
import java.util.*;

public class InventoryDAOImpl implements InventoryDAO {
    private static InventoryDAO instance;
    private final Session session;

    private InventoryDAOImpl() {

        session = FactorySession.openSession();

    }

    public static InventoryDAO getInstance() {
        if (instance==null) instance = new InventoryDAOImpl();
        return instance;
    }


    public boolean create(Inventory inventory) {

        return session.create(inventory);

    }

    // READ
    public List<Inventory> readAll(){

        Session session;
        List<Inventory> inventoryList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Inventory.class);

        inventoryList = new ArrayList<>();

        for (Object object : result.values()) {
            inventoryList.add((Inventory) object);
        }

        session.close();

        return inventoryList;
    }


    public Inventory readByParameter(String byParameter, Object byParameterValue) {

        return ((Inventory) session.readByParameter(Inventory.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(Inventory.class, parameter, byParameter, byParameterValue);

    }



    // UPDATE

    public boolean update(Inventory inventory) {

        return session.update(inventory);

    }

    public boolean updateByParameter(String byParameter, Object byParameterValue) {

        return session.updateByParameter(Inventory.class, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Inventory.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Inventory inventory) {

        return session.delete(inventory);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Inventory.class, byParameter, byParameterValue);

    }
    
}
