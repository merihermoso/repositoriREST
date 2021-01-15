package edu.upc.dsa.orm.dao.user;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.API.RegisterCredentials;
import edu.upc.dsa.orm.models.API.UserRanking;
import edu.upc.dsa.orm.models.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class UserDAOImpl implements UserDAO {

    private static UserDAO instance;
    private final Session session;

    private UserDAOImpl() {

       session = FactorySession.openSession();

    }

    public static UserDAO getInstance() {
        if (instance == null) instance = new UserDAOImpl();
        return instance;
    }


    public boolean create(User user) {

        return session.create(user);

    }



    public boolean exists(String username) {

        return (session.readByParameter(User.class, "username", username) != null);

    }

    public boolean existsId(int id) {

        return (session.readByParameter(User.class, "id", id) != null);

    }

    public boolean existsEmail(String email) {

        return (session.readByParameter(User.class, "email", email) != null);

    }

    public boolean checkPassword(String username, String password) {

        String passwordHash = getHashString(password, "SHA-256");

        String savedPasswordHash = (String) readParameterByParameter("password",
                "username", username);

        return passwordHash.equals(savedPasswordHash);

    }

    public boolean registerUser(RegisterCredentials registerCredentials) {

        String birthdate = registerCredentials.getBirthdate_day() + "/" + registerCredentials.getBirthdate_month() + "/"
                + registerCredentials.getBirthdate_year();

        String passwordHash = getHashString(registerCredentials.getPassword(), "SHA-256");

        User user = new User(registerCredentials.getUsername(), registerCredentials.getEmail(),
                passwordHash, birthdate);

        return session.create(user);

    }

    public User readByParameter(String byParameter, Object byParameterValue) {

        return ((User) session.readByParameter(User.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(User.class, parameter, byParameter, byParameterValue);

    }

    public List<User> readAll(){

        List<User> userList;
        HashMap<Integer, Object> result;

        result = session.readAll(User.class);

        userList = new ArrayList<>();

        for (Object object : result.values()) {
            userList.add((User) object);
        }

        return userList;
    }


    public List<UserRanking> readRanking(){

        HashMap<Integer, User> result;
        result = session.readUserRanking(20);

        List<UserRanking> userRankingResponse = new ArrayList<>();

        int i = 1;

        for(User user : result.values()) {
            userRankingResponse.add(new UserRanking(user.getUsername(),
                    user.getScore(),
                    i));
            i++;
        }

        return userRankingResponse;

    }

    public int readRankingPositionByParameter(String byParameter, Object byParameterValue) {

        return session.readUserRankingPositionByParameter(byParameter, byParameterValue);

    }

    // UPDATE

    public boolean update(User user) {

        return session.update(user);

    }

    public boolean updateByParameter(User user, String byParameter, Object byParameterValue) {

        return session.updateByParameter(user, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(User.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    // DELETE

    public boolean delete(User user) {

        return session.delete(user);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(User.class, byParameter, byParameterValue);

    }



    public String getHashString(String string, String hashType) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();


        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {

            noSuchAlgorithmException.printStackTrace();
            return null;

        }

    }

}