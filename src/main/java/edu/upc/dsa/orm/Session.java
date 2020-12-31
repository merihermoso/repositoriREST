package edu.upc.dsa.orm;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);                           //CRUD
    void close();
    Object get(Class theClass, int ID);                   //CRUD
    void update(Object object);                           //CRUD
    void delete(Object object);                           //CRUD
    List<Object> findAll(Class theClass);                 //CRUD
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);


}
