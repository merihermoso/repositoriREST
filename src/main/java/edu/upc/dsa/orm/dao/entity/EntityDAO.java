package edu.upc.dsa.orm.dao.entity;

import edu.upc.dsa.orm.models.Entity;

import java.util.List;

public interface EntityDAO {


    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(Entity entity);

    // READ
    List<Entity> readAll();
    Entity readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);

    boolean existsId(int id);
    boolean exists(String name);

    // UPDATE
    boolean update(Entity entity);
    boolean updateByParameter(Entity entity, String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);


    // DELETE
    boolean delete(Entity entity);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


}
