package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.User;

import java.sql.SQLException;
import java.util.List;

public interface GameDAO {

      /*****************************************  FUNCIONS GENERALS    ************************************************/
      public List<Game> findAll();
      public int size();

      /*****************************************  OBTENIM PARTIDA     *************************************************/
      public Game getGameById(int gameID) throws SQLException;
      public Game getGameByUsername(String username) throws SQLException;

      /*****************************************  OBTENIM ranking     *************************************************/
      public List<Game> getGameRanking() throws SQLException;


      /*****************************************   REGISTRE PARTIDAS   ************************************************/
      boolean registerGame(GameCredentials gameCredentials) throws SQLException;

      /*****************************************  OBTENIM ranking     *************************************************/
      List<User> getUserRanking() throws SQLException;
      int getUserPositionByUsername(String username);



}
