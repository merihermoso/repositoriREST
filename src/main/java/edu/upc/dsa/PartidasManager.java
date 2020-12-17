package edu.upc.dsa;

import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.User;

import java.util.List;

public interface PartidasManager {

    public int size();

    public Partida addPartida(int score_partida);
    public Partida addPartida(Partida p);
    public Partida getPartida(String id_partida);
    public List<Partida> findAll();
    public void deletePartida(String id_partida);
    public Partida updatePartida(Partida p);

 //   public boolean partidaExists(int id_partida);



}
