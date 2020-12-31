package edu.upc.dsa.orm.dao;

import edu.upc.dsa.orm.models.Partida;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class PartidaManagerImpl implements PartidaManager {
    private static PartidaManager instance;

    protected List<Partida> partidas;

    final static Logger logger = Logger.getLogger(PartidaManagerImpl.class);

    private PartidaManagerImpl() {
        this.partidas = new LinkedList<>();
    }

    public static PartidaManager getInstance() {
        if (instance==null) instance = new PartidaManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.partidas.size();
        logger.info("size partidas =" + ret);
        return ret;
    }

    public Partida addPartida(Partida p) {
        logger.info("new partida to add: " + p);
        this.partidas.add (p);
        logger.info("new Partida added");
        return p;
    }

    public Partida addPartida(int score_partida) {
        return this.addPartida(new Partida(score_partida));
    }

    public Partida getPartida(String id_partida) {
        logger.info("getPartida("+id_partida+")");

        for (Partida p: this.partidas) {
            if (p.getId_partida().equals(id_partida)) {
                logger.info("getPartida("+id_partida+"): "+p);
                return p;
            }
        }
        logger.warn("partida not found with this id: " + id_partida);
        return null;
    }

    @Override
    public List<Partida> findAll() {
        return this.partidas;
    }

    @Override
    public void deletePartida(String id_partida) {
        logger.info("Want to delete partida with this id: " +id_partida);
        Partida p = this.getPartida(id_partida);
        if (p==null) {
            logger.warn("partida not found " +p);
        }
        else logger.info(p+"Partida deleted ");
        this.partidas.remove(p);

    }

    @Override
    public boolean partidaExists(int id_partida) {
        for (Partida t: this.partidas) {

            if (t.getId_partida().equals(id_partida)) {

                return true;

            }

        }
        return false;
    }

    @Override
    public Partida updatePartida(Partida p) {
        Partida u = this.getPartida(p.getId_partida());

        if (u!=null) {
            logger.info(p+" rebut!!!! ");
            u.setScore_partida(p.getScore_partida());
            logger.info(u+"Partida updated ");
        }
        else {
            logger.warn("Partida not found "+p);
        }

        return u;
    }
/*
    public boolean partidaExists(String id_partida) {               //s'hauria de fer d'una altra manera!!!!!!!!

        for (Partida p: this.partidas) {

            if (p.getId_partida().equals(id_partida)) {

                return true;
            }
        }
        return false;
    }*/

}