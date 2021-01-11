package edu.upc.dsa.orm.dao.user;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.API.ChangeEmailCredentials;
import edu.upc.dsa.orm.models.API.ChangePasswordCredentials;
import edu.upc.dsa.orm.models.API.LoginCredentials;
import edu.upc.dsa.orm.models.API.RegisterCredentials;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.models.adminCredentials.ChangeLevel;
import edu.upc.dsa.orm.models.adminCredentials.ChangeScore;
import edu.upc.dsa.orm.models.adminCredentials.ChangeStatus;

import java.sql.SQLException;
import java.util.*;

public class UserDAOImpl implements UserDAO {
    private static UserDAO instance;

    private final int username_min_length;
    private final int username_max_length;
    private final int password_min_length;
    private final int password_max_length;
    private final int email_min_length;
    private final int email_max_length;
    private final int min_age;

    private UserDAOImpl() {

        this.username_min_length = 4;
        this.username_max_length = 20;
        this.password_min_length = 4;
        this.password_max_length = 20;
        this.email_min_length = 4;
        this.email_max_length = 30;
        this.min_age = 15;

    }

    public static UserDAO getInstance() {
        if ( instance==null) instance = new UserDAOImpl();
        return instance;
    }
    /*****************************************  AUTHENTICATION USER   *************************************************/

    public boolean registerUser(RegisterCredentials registerCredentials) throws IllegalAccessException { //Afegeix el user com a obejcte

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.registerUser(registerCredentials);
            session.close();

        } finally {

        }

        return result;

    }



    public boolean userExists(String username) {

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.userExists(username);
            session.close();

        } finally {
        }
        return result;

    }

    public boolean loginUser(LoginCredentials loginCredentials) {

        Session session;
        boolean result = false;

        try {
            session = FactorySession.openSession();
            result = session.loginUser(loginCredentials);
            session.close();
        } finally {

        }

        return result;

    }

    public int getUsername_min_length() {
        return username_min_length;
    }

    public int getUsername_max_length() {
        return username_max_length;
    }

    public int getPassword_min_length() {
        return password_min_length;
    }

    public int getPassword_max_length() {
        return password_max_length;
    }

    public int getEmail_min_length() {
        return email_min_length;
    }

    public int getEmail_max_length() {
        return email_max_length;
    }

    public int getMin_age() {
        return min_age;
    }

    /*****************************************  FUNCIONS BÀSIQUES   *************************************************/

    public List<User> getAllUsers(){
        Session session;
        List<User> userList;
        HashMap<Integer, User> result;

        session = FactorySession.openSession();
        result = session.findAll(User.class);

        userList = new ArrayList<>(result.values());
        session.close();

        return userList;
    }
    public int size() {
        Session session;
        HashMap<String,User> users = null;
        try{
            session = FactorySession.openSession();
            users = session.findAll(User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users.size();
    }

    /*****************************************  modificacions USUARI   *************************************************/
    //Funció per modificar userEmail
    public boolean changeUserEmail(ChangeEmailCredentials changeEmailCredentials) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changeEmail(changeEmailCredentials);

    }
    //Funció per modificar userEmail
    public boolean changeUserPassword(ChangePasswordCredentials changePasswordCredentials) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changePassword(changePasswordCredentials);

    }

    //Funció per modificar userStatus
    public boolean changeUserStatus(ChangeStatus changeStatusCredentials) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changeStatus(changeStatusCredentials);

    }
    //Funció per modificar userEmail
    public boolean changeUserScore(ChangeScore changeScoreCredentials) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changeScore(changeScoreCredentials);

    }
    //Funció per modificar userEmail
    public boolean changeUserLevel(ChangeLevel changeLevelCredentials) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changeLevel(changeLevelCredentials);

    }
    /***********************************************  OBTENIM USUARI  *************************************************/

    public User getUserById( int userID) throws SQLException {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.getById(user, userID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }
    public User getUserByUsername( String username) throws SQLException {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.getUserByUsername(user, username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    //Funció per obtenir userID a partir del seu username
    public int getUserIdByUsername(String username) throws SQLException {

        Session session = null;

        int userID = -1;

        try {

            session = FactorySession.openSession();
            userID = session.getUserIdByUsername(username);          //com poso la relació game User?¿

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return userID;
    }


/***************************************** to do ***************************************************************/
                                                                                        // DELETES  //
/*
    public User deleteUserByUsername(String username) throws SQLException {
        Session session = null;
        User user = new User();
        try {
            session = FactorySession.openSession();
            user = (User) session.deleteUserByUsername(user, username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }
*/
    /**********************************************************************************************************/
    public int updateUser(User user) throws SQLException {
        Session session = null;
        int res=1;
        try {
            session = FactorySession.openSession();
            session.update(user);
            res =0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            return res;
        }
    }
}