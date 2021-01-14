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

    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(User user);
    boolean registerUser(RegisterCredentials registerCredentials);

    // READ
    List<User> readAll();
    User readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);

    boolean userExists(String username);
    boolean checkPassword(String username, String password);
    List<User> readUserRanking();
    int readUserRankingPositionByUsername(String username);

    // UPDATE
    boolean update(User user);
    boolean updateByParameter(String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);

    // DELETE
    boolean delete(User user);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


    // OTHERS
    String getHashString(String string, String hashType);

}
