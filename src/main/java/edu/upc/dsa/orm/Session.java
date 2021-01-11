package edu.upc.dsa.orm;

import edu.upc.dsa.orm.exeptions.UserNotFoundException;
import edu.upc.dsa.orm.models.API.ChangeEmailCredentials;
import edu.upc.dsa.orm.models.API.ChangePasswordCredentials;
import edu.upc.dsa.orm.models.API.LoginCredentials;
import edu.upc.dsa.orm.models.API.RegisterCredentials;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.adminCredentials.*;

import java.sql.SQLException;
import java.util.*;

public interface Session<E> {

    void get(Object entity);
    void save(Object entity) throws IllegalAccessException;
    void update(Object object) throws SQLException, IllegalAccessException;
    void delete(Object object) throws IllegalAccessException;
    void close();

    /*****************************      OBTENIR LLISTAT         *******************************************************/
    HashMap<Integer, Object> findAll(Class theClass);
    HashMap<Integer, Object> findTop(Class theClass);     //el hashmap ho desordena

    /*****************************      autenticación Usuario    ******************************************************/
    boolean registerUser(RegisterCredentials registerCredentials) throws IllegalAccessException;
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    /*****************************      REGISTREM OBJECTES NOUS  (save+Credentials) ***********************************/
    boolean registerGame(GameCredentials gameCredentials) throws IllegalAccessException;
    boolean registerItem(ItemCredentials itemCredentials) throws IllegalAccessException;
    boolean registerEnemy(EnemyCredentials enemyCredentials) throws IllegalAccessException;
    boolean registerPlayer(PlayerCredentials playerCredentials) throws IllegalAccessException;

    /*****************************      MODIFICAR ATRIBUT (Credentials)          **************************************/
    //modificar un únic camp de User
    boolean changeEmail(ChangeEmailCredentials changeEmailCredentials);
    boolean changePassword(ChangePasswordCredentials changePasswordCredentials);
    boolean changeStatus(ChangeStatus changeStatusCredentials);
    boolean changeScore(ChangeScore changeScoreCredentials);
    boolean changeLevel(ChangeLevel changeLevelCredentials);

    //modificar un únic camp de Player
    boolean changePlayerStatus(ChangePlayerStatus changePlayerStatus);
    boolean changePlayerScore(ChangePlayerScore changePlayerScore);
    boolean changePlayerLevel(ChangePlayerLevel changePlayerLevel);
    boolean changePlayerSpeed(ChangePlayerSpeed changePlayerSpeed);
    boolean changePlayerCoins(ChangePlayerCoins changePlayerCoins);

    /*****************************OBTENIM OBJECTES ********************************************************************/
   //Generals, serveixen per totes les clases
    public Object getById(Object theClass, int id) throws SQLException;
    public Object getByName(Object theClass, String name) throws SQLException;

    public Object getUserByUsername(Object theClass, String username) throws SQLException;
    public Object getGameByUsername(Object theClass, String username) throws SQLException;      //es podria optimitzar i aplicar a qualsevol clase (no nomes game)
    public Object getItemByUsername(Object theClass, String username) throws SQLException;   //relaciona 3 taules User,Order,Element
    public Object getPlayerByUsername(Object theClass, String username) throws SQLException;

    /*****************************OBTENIM IDs A PARTIR DEL USERNAME/NAME ********no funcionan pq obtenen un INT *******/
    int getUserIdByUsername(String username);
    int getItemIdByName(String name);

    int getUserPositionByUsername(String username);



//Obtenir atributs del player
    //   public String getCoinsbyUsername(Object theClass, String username) throws SQLException;
    //   public String getLevelbyUsername(Object theClass, String username) throws SQLException;

    HashMap<Integer, Object> getItemsUser(Class theClass, int id_user) throws UserNotFoundException;


    //Funció que retorna els coins de un PLAYER
    int getCoinsPlayer(String id_player);

    //Obtenir Atributs Item
    int getPriceItem(int id_item);
    int getCoinsPlayer(int id_player);




}
