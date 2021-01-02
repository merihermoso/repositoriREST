package edu.upc.dsa.orm.dao.user;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.*;
import edu.upc.dsa.orm.models.User;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.*;

public class UserDAOImpl implements UserDAO {
    private static UserDAO instance;
    protected List<User> users;
    final static Logger logger = Logger.getLogger(UserDAOImpl.class);


    public static UserDAO getInstance() {
        if ( instance==null) instance= new UserDAOImpl();
        return instance;
    }

    public User addUser(User u) {
        logger.info("new User " + u);

        this.users.add (u);
        logger.info("new User added");
        return u;
    }

    public int addUser(String username, String email, String password, int nivel) {
        Session session = null;
        int userID = 0;
        try {
            session = FactorySession.openSession();
            User user = new User(username, email, password, nivel);
            session.save(user);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return userID;
    }


    public User getUser(int userID) {   //obtenim usuari a partir del seu ID
        Session session = null;
        User user = null;
        try {
            session = FactorySession.openSession();
            user = (User)session.get(User.class, userID);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

        return user;
    }


    public void updateUser(int userID, String username, String email, String password, int nivel) {
        User user = this.getUser(userID);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setNivel(nivel);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
    }
    @Override
    public User updateUser(User p) {
        User u = this.getUser(p.getId());

        if (u!=null) {
            logger.info(p+" rebut!!!! ");
                                            //no posem el ID?
            u.setUsername(p.getUsername());
            u.setEmail(p.getEmail());
            u.setPassword(p.getPassword());
            u.setNivel(p.getNivel());

            logger.info(u+" user updated ");
        }
        else {
            logger.warn("user not found "+p);
        }

        return u;
    }

    public void deleteUser(int userID) {
        User user = this.getUser(userID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }

    }

    public List<User> getUsers() {
        return null;
    }


    public List<User> getUser() {
        Session session = null;
        List<User> userList=null;
        try {
            session = FactorySession.openSession();
            userList = session.findAll(User.class);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return userList;
    }
/*
    @Override
    public boolean userExists(int userID) {
        for (User u: this.users) {

            if (u.getId().equals(userID)) {
                return true;
            }

        }
        return false;
    }*/

    public List<User> getUserByPartida(int partidaID) {

        Session session = null;
        List<User> userList=null;
        try {
            session = FactorySession.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("partidaID", partidaID);

            userList = session.findAll(User.class, params);
        }
        catch (Exception e) {
            // LOG
        }
        finally {
            session.close();
        }
        return userList;
    }


}