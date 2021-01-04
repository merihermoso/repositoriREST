package edu.upc.dsa.orm.dao.order;

import edu.upc.dsa.orm.models.Orders;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {

      boolean registerOrder(OrderCredentials orderCredentials);

      public List<Orders> findAll();
      public Orders getOrderById(int orderID) throws SQLException;
      public Orders getOrderByUsername(String username) throws SQLException;

// public Orders addOrderToUser(String username) throws SQLException;           //fer funció asignar Order a User (al apretar el botón "Pagar"
                                    //->pasar parametres LoginCredentials?
}
