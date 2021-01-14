package edu.upc.dsa.orm.dao.player;

import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.Player;
import edu.upc.dsa.orm.models.Player;
import edu.upc.dsa.orm.models.adminCredentials.*;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {

    // CRUD Functions (Create, Read, Update and Delete)

    // CREATE
    boolean create(Player player);

    // READ
    List<Player> readAll();
    Player readByParameter(String byParameter, Object byParameterValue);
    Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);


    // UPDATE
    boolean update(Player player);
    boolean updateByParameter(String byParameter, Object byParameterValue);
    boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue);


    // DELETE
    boolean delete(Player player);
    boolean deleteByParameter(String byParameter, Object byParameterValue);


}
