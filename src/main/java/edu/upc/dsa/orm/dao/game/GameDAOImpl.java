package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.API.GameSettings;
import edu.upc.dsa.orm.models.Game;

import java.util.*;

public class GameDAOImpl implements GameDAO {
    private static GameDAO instance;
    private final Session session;

    private GameDAOImpl() {

        session = FactorySession.openSession();

    }

    public static GameDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new GameDAOImpl();
        return instance;
    }



    public boolean create(Game game) {

        return session.create(game);

    }

    public boolean existsId(int id) {

        return (session.readByParameter(Game.class, "id", id) != null);

    }

    // READ
    public List<Game> readAll(){

        Session session;
        List<Game> gameList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Game.class);

        gameList = new ArrayList<>();

        for (Object object : result.values()) {
            gameList.add((Game) object);
        }

        session.close();

        return gameList;
    }


    public List<Game> readAllByParameter(String byParameter, Object byParameterValue){

        Session session;
        List<Game> gameList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAllByParameter(Game.class, byParameter, byParameterValue);

        gameList = new ArrayList<>();

        for (Object object : result.values()) {
            gameList.add((Game) object);
        }

        session.close();

        return gameList;
    }


    public Game readByParameter(String byParameter, Object byParameterValue) {

        return ((Game) session.readByParameter(Game.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

       return session.readParameterByParameter(Game.class, parameter, byParameter, byParameterValue);

    }

    public GameSettings readSettings() {

        return ((GameSettings) session.readByParameter(GameSettings.class, "id", 1));

    }



    // UPDATE

    public boolean update(Game game) {

        return session.update(game);

    }

    public boolean updateByParameter(Game game, String byParameter, Object byParameterValue) {

        return session.updateByParameter(game, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Game.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Game game) {

        return session.delete(game);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Game.class, byParameter, byParameterValue);

    }


}
