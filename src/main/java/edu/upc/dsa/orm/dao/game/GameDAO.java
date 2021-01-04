package edu.upc.dsa.orm.dao.game;

import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.Orders;

import java.sql.SQLException;
import java.util.List;

public interface GameDAO {

//    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    Partida addPartida(Partida p);

      public Game getGameFromId(int gameID) throws SQLException;
//    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    public void deletePartida(int partidaID);
      public List<Game> findAll();
//    public List <Partida> getPartidasByUserID(int userId);



}
