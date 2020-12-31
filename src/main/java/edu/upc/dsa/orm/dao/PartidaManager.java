package edu.upc.dsa.orm.dao;

import edu.upc.dsa.orm.models.Partida;

import java.util.List;

public interface PartidaManager {

    int size();

    Partida addPartida(int score_partida);
    Partida addPartida(Partida p);

    Partida getPartida(String id_partida);
    List<Partida> findAll();
    Partida updatePartida(Partida p);

    void deletePartida(String id_partida);
    boolean partidaExists(int id_partida);
}
