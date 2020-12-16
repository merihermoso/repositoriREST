package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.List;

public interface UsersManager {

    public int size();

    public User addUser(String username, String pwd);
    public User addUser(User u);
    public User getUser(String id);
    public List<User> findAll();
    public void deleteUser(String id);
    public User updateUser(User u);

    public boolean userExists(String username);
    public boolean checkPassword(String username, String password);





}
