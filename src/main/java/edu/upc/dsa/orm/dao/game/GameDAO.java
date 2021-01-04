package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.models.Game;

import java.sql.SQLException;
import java.util.List;

public interface GameDAO {


      public Game getGameById(int gameID) throws SQLException;
      public Game getGameByUsername(String username) throws SQLException;

      public List<Game> getGameRanking() throws SQLException;

      public List<Game> findAll();




}
