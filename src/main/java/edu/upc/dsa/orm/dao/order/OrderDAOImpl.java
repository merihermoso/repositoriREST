package edu.upc.dsa.orm.dao.order;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Orders;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

import java.sql.SQLException;
import java.util.*;


public class OrderDAOImpl implements OrderDAO {
    private static OrderDAO instance;

    private OrderDAOImpl() {
    }

    public static OrderDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new OrderDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    *************************************************/

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

    public int size() {
        Session session;
        HashMap<String, Orders> orders = null;
        try{
            session = FactorySession.openSession();
            orders = session.findAll(Orders.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders.size();
    }
    /*****************************************  OBTENIM COMANDA     *************************************************/
    public Orders getOrderById(int orderID) throws SQLException {
        Session session = null;
        Orders orders = new Orders();
        try {
            session = FactorySession.openSession();
            orders = (Orders) session.getById(orders, orderID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return orders;
    }
    public Orders getOrderByUsername(String username) throws SQLException {
        Session session = null;
        Orders order = new Orders();
        // User user = new User();
        try {
            session = FactorySession.openSession();
            order = (Orders) session.getOrderByUsername(order, username);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return order;
    }
    /*****************************************  REGISTRE COMANDA     *************************************************/
    public boolean registerOrder(OrderCredentials orderCredentials) { //Afegeix el user com a obejcte

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.registerOrder(orderCredentials);
            session.close();

        } finally {

        }

        return result;

    }

    /*************************************************  TO DO....     *************************************************/

/*
    public boolean addOrdertoUser(LoginCredentials loginCredentials) throws SQLException {

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.orderToUser(loginCredentials);
            session.close();

        } finally {

        }

        return result;

    }*/
    /******************************************************************************************************************/

}
