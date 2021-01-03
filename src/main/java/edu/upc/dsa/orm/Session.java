package edu.upc.dsa.orm;

import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;

import java.util.HashMap;
import java.util.List;

public interface Session<E> {

    void save(Object entity);
    void close();

    HashMap<Integer, Object> findAll(Object entity);

    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

}
