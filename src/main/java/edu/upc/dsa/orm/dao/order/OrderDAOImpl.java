package edu.upc.dsa.orm.dao.order;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Orders;

import java.util.*;


public class OrderDAOImpl implements OrderDAO {
    private static OrderDAO instance;

    private OrderDAOImpl() {
    }

    public static OrderDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new OrderDAOImpl();
        return instance;
    }

    public List<Orders> findAll(){

        Session session;
        List<Orders> ordersList;

        HashMap<Integer, Orders> result;

        session = FactorySession.openSession();
        result = session.findAll(Orders.class);

        ordersList = new ArrayList<>(result.values());

        session.close();

        return ordersList;
    }


}
