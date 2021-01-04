package edu.upc.dsa.orm.dao.order;

import edu.upc.dsa.orm.models.Orders;
import edu.upc.dsa.orm.models.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {


      public List<Orders> findAll();
      public Orders getOrderFromId(int orderID) throws SQLException;



}
