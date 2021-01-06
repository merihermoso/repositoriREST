package edu.upc.dsa.orm.dao.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;

public interface UserDAO {
    /*****************************************  AUTHENTICATION USER   *************************************************/
    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    int getUsername_min_length();
    int getUsername_max_length();
    int getPassword_min_length();
    int getPassword_max_length();
    int getEmail_min_length();
    int getEmail_max_length();
    int getMin_age();
    /*****************************************  FUNCIONS BÀSIQUES  *************************************************/

    List<User> findAll();
    public int size();
    /*****************************************  OBTENIM USUARI  *************************************************/

    public User getUserById( int userID) throws SQLException;
    public User getUserByUsername( String username) throws SQLException;

    /*****************************************  OBTENIM ranking     *************************************************/
    public List<User> getUserRanking() throws SQLException;


    //  public User getUserPositionByUsername(String username) throws SQLException;
    //  public User deleteUserByUsername(String username) throws SQLException;



}
