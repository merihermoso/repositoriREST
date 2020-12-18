package edu.upc.dsa;


import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.Weapone.*;
import org.apache.log4j.Logger;

public class WeaponeManagerImpl implements WeaponeManager{

    private static WeaponeManager instance;

    protected List<Weapone> weapones; //Creamos la lista de weapones

    final static Logger logger = Logger.getLogger(WeaponeManagerImpl.class);

    private WeaponeManagerImpl() { //Constructor
        this.weapones = new LinkedList<>();
    }

    public static WeaponeManager getInstance() {
        if (instance==null) instance = new WeaponeManagerImpl();
        return instance;
    }

    @Override
    public int size() {
        int ret = this.weapones.size();
        logger.info("size weapones =" + ret);

        return ret;
    }

    @Override
    public Weapone addWeapone1(int hit, int damage) {
        return this.addWeapone(new Weapone1(hit, damage));
    }

    @Override
    public Weapone addWeapone2(int hit, int damage) {
        return this.addWeapone(new Weapone2(hit, damage));
    }

    @Override
    public Weapone addWeapone3(int hit, int damage) {
        return this.addWeapone(new Weapone3(hit, damage));
    }

    @Override
    public Weapone addWeapone(Weapone t) {
        logger.info("new weapone to add: " + t);

        this.weapones.add (t);
        logger.info("new weapone added");
        return t;    }

    @Override
    public Weapone getWeapone(String id) {
        logger.info("getWeapone(" + id + ")");

        for (Weapone t: this.weapones) {
            if (t.getId().equals(id)) {
                logger.info("getWeapone(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("weapone not found with this id: " + id);
        return null;
    }


    @Override
    public List<Weapone> findAll() {
        return this.weapones;
    }

    @Override
    public void deleteWeapone(String id) {
        logger.info("Want to delete weapone with this id: " + id);
        Weapone t = this.getWeapone(id);

        if (t==null) { //Comprobamos que la weapone existe
            logger.warn("weapone not found " + t); //No creo que pueda pasar
        }
        else this.weapones.remove(t);
        logger.info(t + "Weapone deleted");
    }
}
