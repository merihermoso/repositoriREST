package edu.upc.dsa.orm.dao.partida;

import edu.upc.dsa.orm.models.Partida;

import java.util.List;

public interface IPartidaDAO {

    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
    public Partida getPartida(int partidaID);
    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
    public void deletePartida(int partidaID);
    public List<Partida> findAll();
    public List <Partida> getPartidaByUserID(int userId);



}
