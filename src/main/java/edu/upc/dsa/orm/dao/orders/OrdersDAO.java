package edu.upc.dsa.orm.dao.orders;

import edu.upc.dsa.orm.models.Inventory;
import edu.upc.dsa.orm.models.Orders;

import java.util.List;

public interface OrdersDAO {

    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(Orders orders);

    // READ
    List<Orders> readAll();
    Orders readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);



    boolean existsId(int id);
    // UPDATE
    boolean update(Orders orders);
    boolean updateByParameter(Orders orders, String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);


    // DELETE
    boolean delete(Orders orders);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


    List<Orders> readAllByParameter(String id_user, int id);
}
