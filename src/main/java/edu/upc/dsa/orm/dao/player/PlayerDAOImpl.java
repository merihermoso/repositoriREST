package edu.upc.dsa.orm.dao.player;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {

    private static PlayerDAO instance;
    private final Session session;

    private PlayerDAOImpl() {

        session = FactorySession.openSession();

    }

    public static PlayerDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new PlayerDAOImpl();
        return instance;
    }

    public boolean create(Player player) {

        return session.create(player);

    }

    // READ
    public List<Player> readAll(){

        Session session;
        List<Player> playerList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Player.class);

        playerList = new ArrayList<>();

        for (Object object : result.values()) {
            playerList.add((Player) object);
        }

        session.close();

        return playerList;
    }


    public Player readByParameter(String byParameter, Object byParameterValue) {

        return ((Player) session.readByParameter(Player.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(Player.class, parameter, byParameter, byParameterValue);

    }



    // UPDATE

    public boolean update(Player player) {

        return session.update(player);

    }

    public boolean updateByParameter(String byParameter, Object byParameterValue) {

        return session.updateByParameter(Player.class, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Player.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Player player) {

        return session.delete(player);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Player.class, byParameter, byParameterValue);

    }
}
