package edu.upc.dsa.orm.dao.item;

import edu.upc.dsa.orm.models.Item;
import java.util.List;

public interface ItemDAO {

    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(Item item);

    // READ
    List<Item> readAll();
    Item readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);

    boolean existsId(int id);
    boolean exists(String name);
    // UPDATE
    boolean update(Item item);
    boolean updateByParameter(Item item, String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);


    // DELETE
    boolean delete(Item item);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


}
