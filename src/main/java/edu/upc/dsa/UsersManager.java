package edu.upc.dsa;

import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.User;

import java.util.List;

public interface UsersManager {

    public int size();

    public User addUser(String username, String pwd); //Registramos un usuario
    public User addUser(User u);
    public User getUser(String id_user);
    public List<User> findAll();
    public void deleteUser(String id_user);
    public User updateUser(User u);

    public boolean userExists(String username);
    public boolean checkPassword(String username, String password);




}
