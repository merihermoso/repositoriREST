package edu.upc.dsa.orm;

import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

import java.sql.SQLException;
import java.util.*;

public interface Session<E> {

    void save(Object entity);
    void close();

    HashMap<Integer, Object> findAll(Class theClass);
    HashMap<Integer, Object> findTop(Class theClass);  //Busca els millors SCORE    List<User> users?¿ nose si els desordena per això

    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    boolean registerOrder(OrderCredentials orderCredentials);

    boolean registerElement(ElementCredentials elementCredentials);

  //  boolean orderToUser(LoginCredentials loginCredentials);           //NO CONSEGUEIXO QUE INSERTI A LA TAULA DE RELACIÓ
   // boolean createGAME(createGameCredentials gameCredentials) throws SQLException;        //seria un insert partida...

    public Object getById(Object theClass, int id) throws SQLException;


    public Object getUserByUsername(Object theClass, String username) throws SQLException;
    public Object getGameByUsername(Object theClass, String username) throws SQLException;      //es podria optimitzar i aplicar a qualsevol clase (no nomes game)
    public Object getOrderByUsername(Object theClass, String username) throws SQLException;
    public Object getElementByUsername(Object theClass, String username) throws SQLException;   //relaciona 3 taules User,Order,Element


}
