package edu.upc.dsa.orm.dao.user;

import java.util.List;
import edu.upc.dsa.orm.models.User;

public interface UserDAO {


    public int addUser(String username, String email, String password, int nivel);
    public User addUser(User u);
    public User getUser(int userID);
    public List<User> findAll();

    public void deleteUser(int userID);
    public void updateUser(int userID, String username, String email, String password, int nivel);
    public User updateUser(User u);

    public List <User> getUserByPartida(int partidaId);

  //  public boolean userExists(int userID);
}
