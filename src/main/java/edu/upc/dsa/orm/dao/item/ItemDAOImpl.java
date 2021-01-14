package edu.upc.dsa.orm.dao.item;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.*;
import java.util.*;


public class ItemDAOImpl implements ItemDAO {

    private static ItemDAO instance;
    private final Session session;

    private ItemDAOImpl() {

        session = FactorySession.openSession();

    }

    public static ItemDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new ItemDAOImpl();
        return instance;
    }



    public boolean create(Item item) {

        return session.create(item);

    }

    // READ
    public List<Item> readAll(){

        Session session;
        List<Item> itemList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Item.class);

        itemList = new ArrayList<>();

        for (Object object : result.values()) {
            itemList.add((Item) object);
        }

        session.close();

        return itemList;
    }


    public Object readByParameter(String byParameter, Object byParameterValue) {

        return session.readByParameter(Item.class, byParameter, byParameterValue);

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(Item.class, parameter, byParameter, byParameterValue);

    }



    // UPDATE

    public boolean update(Item item) {

        return session.update(item);

    }

    public boolean updateByParameter(Item item, String byParameter, Object byParameterValue) {

        return session.updateByParameter(item, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Item.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Item item) {

        return session.delete(item);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Item.class, byParameter, byParameterValue);

    }
}
