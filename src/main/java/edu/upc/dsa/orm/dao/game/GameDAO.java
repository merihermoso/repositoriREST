package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.models.Game;
import java.util.List;

public interface GameDAO {

      // CRUD Functions (Create, Read, Update and Delete)

      // CREATE
      boolean create(Game game);

      // READ
      List<Game> readAll();
      Game readByParameter(String byParameter, Object byParameterValue);
      Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);


      // UPDATE
      boolean update(Game game);
      boolean updateByParameter(Game game, String byParameter, Object byParameterValue);
      boolean updateParameterByParameter(String parameter, Object parameterValue
              , String byParameter, Object byParameterValue);


      // DELETE
      boolean delete(Game game);
      boolean deleteByParameter(String byParameter, Object byParameterValue);


}
