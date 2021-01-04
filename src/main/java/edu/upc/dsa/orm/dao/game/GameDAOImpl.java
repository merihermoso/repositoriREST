package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.Orders;
//import jdk.incubator.jpackage.internal.Log;

import java.sql.SQLException;
import java.util.*;

public class GameDAOImpl implements GameDAO {
    private static GameDAO instance;

    private GameDAOImpl() {
    }

    public static GameDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new GameDAOImpl();
        return instance;
    }

    public List<Game> findAll(){

        Session session;
        List<Game> gamesList;

        HashMap<Integer, Game> result;

        session = FactorySession.openSession();
        result = session.findAll(Game.class);

        gamesList = new ArrayList<>(result.values());

        session.close();

        return gamesList;
    }
    public Game getGameFromId(int gameID) throws SQLException {
        Session session = null;
        Game game = new Game();
        try {
            session = FactorySession.openSession();
            game = (Game) session.getFromId(game, gameID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return game;
    }

}
