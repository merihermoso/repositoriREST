package edu.upc.dsa.orm.dao.partida;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Partida;
import edu.upc.dsa.orm.util.RandomUtils;
//import jdk.incubator.jpackage.internal.Log;
import org.apache.log4j.Logger;
import java.util.concurrent.ThreadLocalRandom;

import java.util.*;

public class PartidaDAOImpl implements PartidaDAO {
    protected List<Partida> partidas;                       //llista que creem a memoria (S'hauria de passar a bbdd)
    private static PartidaDAO instance;
    final static Logger logger = Logger.getLogger(PartidaDAOImpl.class);

    private PartidaDAOImpl() {
        this.partidas = new LinkedList<>();
    }           //cuando no hay bbdd
    /*
    public static PartidaDAOImpl getInstance() {                    //DA ERROR
        if (instance==null) instance = new PartidaDAOImpl();
        return instance;
    }*/

    public int addPartida(String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score) {
        Session session = null;             //nose si això només es al usuari o a totes les clases  ?¿
        int partidaID = 0;          //HAURIEM DE POSAR QUE ET GENERI UN INT RANDOM
        try {
            session = FactorySession.openSession();
            Partida partida = new Partida(fechaInicio,horaInicio,fechaFin,horaFin,score);
            session.save(partida);
        }
        catch (Exception e) {
           // Log.error("ERROR al añadir partida: " + e);
        }
        finally {
            session.close();
        }
        return partidaID;
    }
    public Partida addPartida(Partida p) {                  //NO ESTÀ IMPLEMENTAT LA BBDD
        logger.info("new partida to add: " + p);
        this.partidas.add (p);
        logger.info("new Partida added");
        return p;
    }

    public Partida getPartida(int partidaID) {
        Session session = null;
        Partida partida = null;
        try {
            session = FactorySession.openSession();
            partida = (Partida)session.get(Partida.class, partidaID);
        }
        catch (Exception e) {
          //  Log.error("ERROR al obtener partida: " + e);
        }
        finally {
            session.close();
        }

        return partida;
    }




    public void updatePartida(int partidaID, String fechaInicio, String horaInicio, String fechaFin, String horaFin, int score) {
        Partida partida = this.getPartida(partidaID);
        partida.setFechaInicio(fechaInicio);
        partida.setHoraInicio(horaInicio);
        partida.setFechaFin(fechaFin);
        partida.setHoraFin(horaFin);
        partida.setScore(score);

        Session session = null;
        try {
            session = FactorySession.openSession();
            session.update(Partida.class);
        }
        catch (Exception e) {
           // Log.error("ERROR al modificar partida: " + e);
        }
        finally {
            session.close();
        }
    }


    public void deletePartida(int partidaID) {
        Partida partida= this.getPartida(partidaID);
        Session session = null;
        try {
            session = FactorySession.openSession();
            session.delete(Partida.class);
        }
        catch (Exception e) {
          //  Log.error("ERROR al eliminar partida: " + e);
        }
        finally {
            session.close();
        }

    }



    public List<Partida> findAll() {
        Session session = null;
        List<Partida> partidaList=null;
        try {
            session = FactorySession.openSession();
            partidaList = session.findAll(Partida.class);
        }
        catch (Exception e) {
         //   Log.error("ERROR al findAll partida: " + e);
        }
        finally {
            session.close();
        }
        return partidaList;
    }


    public List<Partida> getPartidasByUserID(int userID) {

        Session session = null;
        List<Partida> partidaList=null;
        try {
            session = FactorySession.openSession();

            HashMap<String, Integer> params = new HashMap<String, Integer>();
            params.put("userID", userID);

            partidaList = session.findAll(Partida.class, params);
        }
        catch (Exception e) {
          //  Log.error("ERROR al findAll partidas del user: " +e+userID);
        }
        finally {
            session.close();
        }
        return partidaList;
    }


}
