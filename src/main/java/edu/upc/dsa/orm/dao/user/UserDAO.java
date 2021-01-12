package edu.upc.dsa.orm.dao.user;

import java.sql.SQLException;
import java.util.List;

import edu.upc.dsa.orm.models.API.ChangeEmailCredentials;
import edu.upc.dsa.orm.models.API.ChangePasswordCredentials;
import edu.upc.dsa.orm.models.API.LoginCredentials;
import edu.upc.dsa.orm.models.API.RegisterCredentials;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.adminCredentials.ChangeLevel;
import edu.upc.dsa.orm.models.adminCredentials.ChangeScore;
import edu.upc.dsa.orm.models.adminCredentials.ChangeStatus;

public interface UserDAO {
    /*****************************************  AUTHENTICATION USER   *************************************************/
    boolean registerUser(RegisterCredentials registerCredentials) throws IllegalAccessException;
    boolean loginUser(LoginCredentials loginCredentials);
    boolean userExists(String username);

    /*****************************************  modificacions USER   **************************************************/
   // canviar user senser
    public int updateUser(User user)throws SQLException;

    boolean changeUserPassword(ChangePasswordCredentials changePasswordCredentials);
    boolean changeUserEmail(ChangeEmailCredentials changeEmailCredentials);

    //funcions admin
    boolean changeUserStatus(ChangeStatus changeStatusCredentials);
    boolean changeUserScore(ChangeScore changeScoreCredentials);
    boolean changeUserLevel(ChangeLevel changeLevelCredentials);


    /*****************************************  FUNCIONS BÀSIQUES    **************************************************/
    List<User> getAllUsers();
    int size();

    /*****************************************  OBTENIM USUARI       **************************************************/
   //Obtenim objecte
    User getUserById( int userID) throws SQLException;
    User getUserByUsername( String username) throws SQLException;

    //Obtenim un atribut
    int getUserIdByUsername(String username) throws SQLException;




    //  public User deleteUserByUsername(String username) throws SQLException;

}
