package edu.upc.dsa.orm.dao.user;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.User;

public interface UserDAO {

    boolean registerUser(RegisterCredentials registerCredentials);
    boolean loginUser(LoginCredentials loginCredentials);

    boolean userExists(String username);

    List<User> findAll();

    int getUsername_min_length();
    int getUsername_max_length();
    int getPassword_min_length();
    int getPassword_max_length();
    int getEmail_min_length();
    int getEmail_max_length();
    int getMin_age();

    public User getUserById( int userID) throws SQLException;
    public User getUserByUsername( String username) throws SQLException;

    public List<User> getUserRanking() throws SQLException;

}
