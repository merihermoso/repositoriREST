package edu.upc.dsa.orm.dao.order;

import edu.upc.dsa.orm.models.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {


      public List<Orders> findAll();
      public Orders getOrderById(int orderID) throws SQLException;
      public Orders getOrderByUsername(String username) throws SQLException;



}
