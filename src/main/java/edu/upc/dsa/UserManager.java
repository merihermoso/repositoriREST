package edu.upc.dsa;


import edu.upc.dsa.models.User;

import java.util.List;

public interface UserManager {

    int size();

    User addUser(String username, String pwd, String email, String birthdate); //Registramos un usuario
    User addUser(User u);

    User getUser(String id_user);
    User updateUser(User u);
    List<User> findAll();
    void deleteUser(String id_user);

    boolean userExists(String username);
    boolean checkPassword(String username, String password);




}
