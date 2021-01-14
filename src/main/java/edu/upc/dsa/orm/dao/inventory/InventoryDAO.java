package edu.upc.dsa.orm.dao.inventory;

import edu.upc.dsa.orm.models.Inventory;
import edu.upc.dsa.orm.models.Inventory;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO {

    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(Inventory inventory);

    // READ
    List<Inventory> readAll();
    Inventory readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);


    // UPDATE
    boolean update(Inventory inventory);
    boolean updateByParameter(String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);


    // DELETE
    boolean delete(Inventory inventory);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


}
