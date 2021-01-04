package edu.upc.dsa.orm;

import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.Item;

import java.util.HashMap;

public interface Session<E> {

    void save(Object entity);
    void close();

    HashMap<Integer, Object> findAll(Class theClass);

    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    void AddItem(Item item);

}
