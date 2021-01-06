package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.User;
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
    /*****************************************  FUNCIONS GENERALS    ************************************************/
    public List<Game> findAll(){

        Session session;
        List<Game> gameList;

        HashMap<Integer, Game> result;

        session = FactorySession.openSession();
        result = session.findAll(Game.class);

        gameList = new ArrayList<>(result.values());

        session.close();

        return gameList;
    }
    public int size() {
        Session session;
        HashMap<String, Game> games = null;
        try{
            session = FactorySession.openSession();
            games = session.findAll(Game.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return games.size();
    }

    /*****************************************  OBTENIM PARTIDA     *************************************************/
        public Game getGameById(int gameID) throws SQLException {
        Session session = null;
        Game game = new Game();
        try {
            session = FactorySession.openSession();
            game = (Game) session.getById(game, gameID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return game;
    }

    public Game getGameByUsername(String username) throws SQLException {
        Session session = null;
        Game game = new Game();
      // User user = new User();
        try {
            session = FactorySession.openSession();
            game = (Game) session.getGameByUsername(game, username);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return game;
    }
    /*****************************************  OBTENIM ranking     *************************************************/
    public List<Game> getGameRanking(){

        Session session;
        List<Game> gamesList;

        HashMap<Integer, Game> result;

        session = FactorySession.openSession();
        result = session.findTop(Game.class);

        gamesList = new ArrayList<>(result.values());

        session.close();

        return gamesList;
    }
    /*****************************************   REGISTRE PARTIDAS   ************************************************/

    public boolean registerGame(GameCredentials gameCredentials) { //Afegeix el user com a obejcte

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.registerGame(gameCredentials);
            session.close();

        } finally {

        }

        return result;

    }
    /**********************************************************************************************************/
}
