package edu.upc.dsa;


import edu.upc.dsa.models.Defense.*;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class DefenseManagerImpl implements DefenseManager {
    private static DefenseManager instance;

    protected List<Defense> defenses; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(UserManagerImpl.class);

    private DefenseManagerImpl() { //Constructor
        this.defenses = new LinkedList<>();
    }

    public static DefenseManager getInstance() {
        if (instance==null) instance = new DefenseManagerImpl();
        return instance;
    }

    @Override
    public int size() {
        int ret = this.defenses.size();
        logger.info("size defences =" + ret);

        return ret;
    }

    @Override
    public Defense addDefense1(int hit, int force) {
        return this.addDefense(new Defense1(hit, force));
    }

    @Override
    public Defense addDefense2(int hit, int force) {
        return this.addDefense(new Defense2(hit, force));
    }

    @Override
    public Defense addDefense3(int hit, int force) {
        return this.addDefense(new Defense3(hit, force));
    }

    @Override
    public Defense addDefense(Defense p) {
        logger.info("new defense to add: " + p);

        this.defenses.add (p);
        logger.info("new Defense added");
        return p;
    }

    @Override
    public Defense getDefense(String id) {
        logger.info("getDefense(" + id + ")");

        for (Defense t: this.defenses) {
            if (t.getId().equals(id)) {
                logger.info("getDefenses(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("defenses not found with this id: " + id);
        return null;
    }

    @Override
    public List<Defense> findAll() {
        return this.defenses;
    }

    @Override
    public void deleteDefense(String id) {
        logger.info("Want to delete defence with this id: " + id);
        Defense t = this.getDefense(id);

        if (t==null) { //Comprobamos que el objeto de defensa existe
            logger.warn("defense not found " + t); //No creo que pueda pasar
        }
        else this.defenses.remove(t);
        logger.info(t + "Defense deleted");
    }
}
