package edu.upc.dsa.orm.dao.order;

import edu.upc.dsa.orm.models.Orders;

import java.util.List;

public interface OrderDAO {

//    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    Partida addPartida(Partida p);

//    public Partida getPartida(int partidaID);
//    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    public void deletePartida(int partidaID);
      public List<Orders> findAll();
//    public List <Partida> getPartidasByUserID(int userId);



}
