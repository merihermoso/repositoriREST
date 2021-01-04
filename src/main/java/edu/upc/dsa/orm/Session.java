package edu.upc.dsa.orm;

import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.*;

import java.sql.SQLException;
import java.util.*;

public interface Session<E> {

    void save(Object entity);
    void close();

    HashMap<Integer, Object> findAll(Class theClass);


    HashMap<Integer, Object> findTop(Class theClass);          //s'haurien de unificar


    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    public Object getById(Object theClass, int id) throws SQLException;
    public Object getUserByUsername(Object theClass, String username) throws SQLException;

    public Object getGameByUsername(Object theClass, String username) throws SQLException;      //es podria optimitzar i aplicar a qualsevol clase (no nomes game)
    public Object getOrderByUsername(Object theClass, String username) throws SQLException;
                                                                                                //relaciona 3 taules User,Order,Element
    public Object getElementByUsername(Object theClass, String username) throws SQLException;
}
