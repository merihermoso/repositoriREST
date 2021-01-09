package edu.upc.dsa.orm.dao.player;

//import com.sun.tools.javac.jvm.Items;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.Player;
import edu.upc.dsa.orm.models.adminCredentials.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {
    private static PlayerDAO instance;

    private PlayerDAOImpl() {
    }

    public static PlayerDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new PlayerDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Player> findAll(){

        Session session;
        List<Player> playersList;

        HashMap<Integer, Player> result;

        session = FactorySession.openSession();
        result = session.findAll(Player.class);

        playersList = new ArrayList<>(result.values());

        session.close();

        return playersList;
    }
    public int size() {
        Session session;
        HashMap<String, Player> players = null;
        try{
            session = FactorySession.openSession();
            players = session.findAll(Item.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players.size();
    }

    /*****************************************  OBTENIM Player  ****************************************************/

    public Player getPlayerById(int playerID) throws SQLException {
        Session session = null;
        Player player = new Player();
        try {
            session = FactorySession.openSession();
            player = (Player) session.getById(player, playerID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return player;
    }

    //Obtenim l'objecte element a partir del username del usuari propietari
    public Player getPlayerByUsername(String username) throws SQLException {
        Session session = null;
        Player player = new Player();
        try {
            session = FactorySession.openSession();
            player = (Player) session.getPlayerByUsername(player, username);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return player;
    }

    /*****************************************  REGISTRE ITEM     *************************************************/
    public boolean registerPlayer(PlayerCredentials playerCredentials) { //Afegeix el user com a obejcte
        Session session;
        boolean result = false;

        try {
            session = FactorySession.openSession();
            result = session.registerPlayer(playerCredentials);
            session.close();
        } finally {
        }
        return result;
    }
    /**********************************************************************************************************/
//Funció per modificar playerStatus
    public boolean changePlayerStatus(ChangePlayerStatus changePlayerStatus) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changePlayerStatus(changePlayerStatus);

    }
    //Funció per modificar playerScore
    public boolean changePlayerScore(ChangePlayerScore changePlayerScore) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changePlayerScore(changePlayerScore);

    }
    //Funció per modificar playerLevel
    public boolean changePlayerLevel(ChangePlayerLevel changePlayerLevel) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changePlayerLevel(changePlayerLevel);

    }
    //Funció per modificar playerScore
    public boolean changePlayerSpeed(ChangePlayerSpeed changePlayerSpeed) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changePlayerSpeed(changePlayerSpeed);

    }
    //Funció per modificar playerLevel
    public boolean changePlayerCoins(ChangePlayerCoins changePlayerCoins) {

        Session session;

        session = FactorySession.openSession();
        session.close();

        return session.changePlayerCoins(changePlayerCoins);

    }
}
