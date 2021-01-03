package edu.upc.dsa.orm.dao.element;

import edu.upc.dsa.orm.models.Elements;

import java.util.*;

public interface ElementDAO {

//    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    Partida addPartida(Partida p);

//    public Partida getPartida(int partidaID);
//    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    public void deletePartida(int partidaID);
    public List<Elements> findAll();
//    public List <Partida> getPartidasByUserID(int userId);



}
