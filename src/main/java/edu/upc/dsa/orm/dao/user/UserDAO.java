package edu.upc.dsa.orm.dao.user;

import java.sql.SQLException;
import java.util.List;

import edu.upc.dsa.orm.models.Credentials.*;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.adminCredentials.ChangeLevel;
import edu.upc.dsa.orm.models.adminCredentials.ChangePlayerScore;
import edu.upc.dsa.orm.models.adminCredentials.ChangeScore;
import edu.upc.dsa.orm.models.adminCredentials.ChangeStatus;

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

    /*****************************************  modificacions USER   **************************************************/
    boolean changeUserPassword(ChangePasswordCredentials changePasswordCredentials);
    boolean changeUserEmail(ChangeEmailCredentials changeEmailCredentials);
    boolean changeUserBirthday(ChangeBirthdayCredentials changeBirthdayCredentials);

    //funcions admin
    boolean changeUserStatus(ChangeStatus changeStatusCredentials);
    boolean changeUserScore(ChangeScore changeScoreCredentials);
    boolean changeUserLevel(ChangeLevel changeLevelCredentials);


    /*****************************************  FUNCIONS BÀSIQUES    **************************************************/
    List<User> findAll();
    int size();

    /*****************************************  OBTENIM USUARI       **************************************************/
   //Obtenim objecte
    User getUserById( int userID) throws SQLException;
    User getUserByUsername( String username) throws SQLException;

    //Obtenim un atribut
    int getUserIdByUsername(String username) throws SQLException;
    int getUserPositionByUsername(String username) throws SQLException;

    /*****************************************  OBTENIM ranking     *************************************************/
    public List<User> getUserRanking() throws SQLException;




    //  public User deleteUserByUsername(String username) throws SQLException;





}
