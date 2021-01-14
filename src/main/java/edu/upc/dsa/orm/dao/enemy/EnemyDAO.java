package edu.upc.dsa.orm.dao.enemy;

import edu.upc.dsa.orm.models.Enemy;

import java.util.List;

public interface EnemyDAO {


    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(Enemy enemy);

    // READ
    List<Enemy> readAll();
    Enemy readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);


    // UPDATE
    boolean update(Enemy enemy);
    boolean updateByParameter(String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);


    // DELETE
    boolean delete(Enemy enemy);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


}
