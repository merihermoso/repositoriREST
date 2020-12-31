package edu.upc.dsa.orm.dao;

import java.util.List;
import edu.upc.dsa.orm.models.User;

public interface IUserDAO {

    public int addUser(String username, String email, String password, int nivel);
    public User getUser(int userID);
    public void updateUser(int userID, String username, String email, String password, int nivel);
    public void deleteUser(int userID);
    public List<User> getUsers();
    public List <User> getUserByPartida(int partidaId);
}
