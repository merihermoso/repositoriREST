package edu.upc.dsa.orm.dao.element;

import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.Orders;

import java.sql.SQLException;
import java.util.*;

public interface ElementDAO {

//    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    Partida addPartida(Partida p);

    public Element getElementFromId(int elementID) throws SQLException;
//    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score);
//    public void deletePartida(int partidaID);
    public List<Element> findAll();
//    public List <Partida> getPartidasByUserID(int userId);



}
