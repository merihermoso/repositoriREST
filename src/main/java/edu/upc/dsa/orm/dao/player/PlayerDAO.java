package edu.upc.dsa.orm.dao.player;

import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {

    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Player> findAll();
    public int size();

    /*****************************************  OBTENIM ITEM inventari
     * @return*************************************************/
    public Player getPlayerById(int playerID) throws SQLException;
    public Player getPlayerByUsername(String username) throws SQLException;

    /*****************************************  REGISTRE ITEM     *************************************************/
    boolean registerPlayer(PlayerCredentials playerCredentials) throws SQLException;


}
