package edu.upc.dsa.orm.dao.orders;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.Orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    private static OrdersDAO instance;
    private final Session session;

    private OrdersDAOImpl() {

        session = FactorySession.openSession();

    }

    public static OrdersDAO getInstance() {
        if (instance==null) instance = new OrdersDAOImpl();
        return instance;
    }


    public boolean create(Orders orders) {

        return session.create(orders);

    }

    public boolean existsId(int id) {

        return (session.readByParameter(Item.class, "id", id) != null);

    }

    // READ
    public List<Orders> readAll(){

        Session session;
        List<Orders> ordersList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Orders.class);

        ordersList = new ArrayList<>();

        for (Object object : result.values()) {
            ordersList.add((Orders) object);
        }

        session.close();

        return ordersList;
    }


    public Orders readByParameter(String byParameter, Object byParameterValue) {

        return ((Orders) session.readByParameter(Orders.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(Orders.class, parameter, byParameter, byParameterValue);

    }



    // UPDATE

    public boolean update(Orders orders) {

        return session.update(orders);

    }

    public boolean updateByParameter(Orders orders, String byParameter, Object byParameterValue) {

        return session.updateByParameter(orders, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Orders.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Orders orders) {

        return session.delete(orders);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Orders.class, byParameter, byParameterValue);

    }
    
}
