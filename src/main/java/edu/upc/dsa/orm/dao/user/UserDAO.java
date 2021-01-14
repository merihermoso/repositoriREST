package edu.upc.dsa.orm.dao.user;
import java.util.List;
import edu.upc.dsa.orm.models.API.RegisterCredentials;
import edu.upc.dsa.orm.models.API.UserRanking;
import edu.upc.dsa.orm.models.User;

public interface UserDAO {

    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(User user);
    boolean registerUser(RegisterCredentials registerCredentials);

    // READ
    List<User> readAll();
    User readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);

    boolean exists(String username);
    boolean existsId(int id);
    boolean checkPassword(String username, String password);
    List<UserRanking> readRanking();
    int readRankingPositionByParameter(String byParameter, Object byParameterValue);

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
