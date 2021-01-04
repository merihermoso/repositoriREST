package edu.upc.dsa.orm.dao.item;

import edu.upc.dsa.orm.models.Item;

import java.util.List;

public interface ItemDAO {

//    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    Partida addPartida(Partida p);

//    public Partida getPartida(int partidaID);
//    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    public void deletePartida(int partidaID);
    public List<Item> findAll();
//    public List <Partida> getPartidasByUserID(int userId);



}
