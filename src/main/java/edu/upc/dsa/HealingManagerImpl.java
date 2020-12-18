package edu.upc.dsa;


import edu.upc.dsa.models.Healing.*;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class HealingManagerImpl implements HealingManager{

    private static HealingManager instance;

    protected List<Healing> healings; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(HealingManagerImpl.class);

    private HealingManagerImpl() { //Constructor
        this.healings = new LinkedList<>();
    }

    public static HealingManager getInstance() {
        if (instance==null) instance = new HealingManagerImpl();
        return instance;
    }

    @Override
    public int size() {
        int ret = this.healings.size();
        logger.info("size healings =" + ret);

        return ret;
    }

    @Override
    public Healing addHealing1(int hit, int force) {
        return this.addHealing(new Healing1(hit, force));
    }

    @Override
    public Healing addHealing2(int hit, int force) {
        return this.addHealing(new Healing2(hit, force));
    }

    @Override
    public Healing addHealing3(int hit, int force) {
        return this.addHealing(new Healing3(hit, force));
    }

    @Override
    public Healing addHealing(Healing t) {
        logger.info("new healing to add: " + t);

        this.healings.add (t);
        logger.info("new Healing added");
        return t;
    }

    @Override
    public Healing getHelaing(String id) {
        logger.info("getHealing(" + id + ")");

        for (Healing t: this.healings) {
            if (t.getId().equals(id)) {
                logger.info("getHealing(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("healing not found with this id: " + id);
        return null;
    }

    @Override
    public List<Healing> findAll() {
        return this.healings;
    }

    @Override
    public void deleteHealing(String id) {
        logger.info("Want to delete healing with this id: " + id);
        Healing t = this.getHelaing(id);

        if (t==null) { //Comprobamos que el healing existe
            logger.warn("healing not found " + t); //No creo que pueda pasar
        }
        else this.healings.remove(t);
        logger.info(t + "Healing deleted");
    }
}
