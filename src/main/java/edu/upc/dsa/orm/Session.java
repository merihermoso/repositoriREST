package edu.upc.dsa.orm;

import edu.upc.dsa.orm.models.Credentials.ChangeEmailCredentials;
import edu.upc.dsa.orm.models.Credentials.ChangePasswordCredentials;
import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.Enemy;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

import java.sql.SQLException;
import java.util.*;

public interface Session<E> {

    void save(Object entity);
    void update(Object object);
    void delete(Object object);
    void close();

    /*****************************      OBTENIR LLISTAT         *******************************************************/
    HashMap<Integer, Object> findAll(Class theClass);
    HashMap<Integer, Object> findTop(Class theClass);     //el hashmap ho desordena

    /*****************************      autenticación Usuario    ******************************************************/
    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    /*****************************      REGISTREM OBJECTES NOUS  (INSERT) *********************************************/
    boolean registerElement(ElementCredentials elementCredentials);
    boolean registerGame(GameCredentials gameCredentials);
    boolean registerItem(ItemCredentials itemCredentials);
    boolean registerEnemy(EnemyCredentials enemyCredentials);
    boolean registerPlayer(PlayerCredentials playerCredentials);

    /*****************************OBTENIM OBJECTES A PARTIR DEL USERNAME DEL USER**************************************/
    public Object getById(Object theClass, int id) throws SQLException;             //obtenim objecte a partir del id
    public Object getByName(Object theClass, String name) throws SQLException;      //obtenim objecte a partir del seu nom


    /*****************************OBTENIM IDs A PARTIR DEL USERNAME/NAME ********no funcionan pq obtenen un INT *******/
    int getUserIdByUsername(String username);
    int getElementIdByName(String name);

    int getUserPositionByUsername(String username);

    boolean changeEmail(ChangeEmailCredentials changeEmailCredentials);
    boolean changePassword(ChangePasswordCredentials changePasswordCredentials);

    /*****************************OBTENIM OBJECTES A PARTIR DEL USERNAME DEL USER*******************************/
    public Object getUserByUsername(Object theClass, String username) throws SQLException;
    public Object getGameByUsername(Object theClass, String username) throws SQLException;      //es podria optimitzar i aplicar a qualsevol clase (no nomes game)
    public Object getElementByUsername(Object theClass, String username) throws SQLException;   //relaciona 3 taules User,Order,Element
    public Object getPlayerByUsername(Object theClass, String username) throws SQLException;

    //   public String getCOINSbyUsername(Object theClass, String username) throws SQLException;
    //   public String getLEVELbyUsername(Object theClass, String username) throws SQLException;


}
