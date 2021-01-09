package edu.upc.dsa.orm.dao.player;

import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.Player;
import edu.upc.dsa.orm.models.adminCredentials.*;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {

    /*****************************************  FUNCIONS GENERALS    ***************************************************/
    public List<Player> findAll();
    public int size();

    /*****************************************  OBTENIM ITEM inventari  ************************************************/
    public Player getPlayerById(int playerID) throws SQLException;
    public Player getPlayerByUsername(String username) throws SQLException;

    /*****************************************  REGISTRE ITEM     *****************************************************/
    boolean registerPlayer(PlayerCredentials playerCredentials) throws SQLException;

    /***************************************** funcions per modificar *************************************************/
    boolean changePlayerStatus(ChangePlayerStatus changePStatus);
    boolean changePlayerCoins(ChangePlayerCoins changePCoins);
    boolean changePlayerScore(ChangePlayerScore changePScore);
    boolean changePlayerLevel(ChangePlayerLevel changePLevel);
    boolean changePlayerSpeed(ChangePlayerSpeed changePSpeed);

}
