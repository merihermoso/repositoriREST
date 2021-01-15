package edu.upc.dsa.orm;


import edu.upc.dsa.orm.models.User;
import java.util.*;

public interface Session {

    void close();

    // CRUD

    // CREATE
    boolean create(Object object);

    // READ
    HashMap<Integer, Object> readAll(Class theClass);
    Object readByParameter(Class theClass, String byParameter, Object byParameterValue);
    Object readParameterByParameter(Class theClass, String parameter, String byParameter, Object byParameterValue);

    // UPDATE
    boolean update(Object object);
    boolean updateByParameter(Object object, String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(Class theClass, String parameter,
                                              Object parameterValue, String byParameter, Object byParameterValue);

    // DELETE
    boolean delete(Object object);
    boolean deleteByParameter(Class theClass, String byParameter, Object byParameterValue);

    /*****************************     Exclusivo Users    ******************************************************/
    HashMap<Integer, User> readUserRanking(int limit);
    int readUserRankingPositionByParameter(String byParameter, Object byParameterValue);

}
