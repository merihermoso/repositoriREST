package edu.upc.dsa.orm.dao;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.Defense.*;
import edu.upc.dsa.orm.models.Healing.*;
import edu.upc.dsa.orm.models.Weapone.*;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class ChestManagerImpl implements ChestManager{

    private static ChestManager instance;

    protected List<Item> items; //Creamos la lista items en el chest

    final static Logger logger = Logger.getLogger(ChestManagerImpl.class);

    private ChestManagerImpl() { //Constructor
        this.items = new LinkedList<>();
    }

    public static ChestManager getInstance() {
        if (instance==null) instance = new ChestManagerImpl();
        return instance;
    }


    public int size() {
        int ret = this.items.size();
        logger.info("size items in chest =" + ret);

        return ret;
    }

    @Override
    public Defense addDefense1() {
        return this.addDefense(new Defense1());
    }

    @Override
    public Defense addDefense2() {
        return this.addDefense(new Defense2());
    }

    @Override
    public Defense addDefense3() {
        return this.addDefense(new Defense3());
    }


    @Override
    public Healing addHealing1() {
        return this.addHealing(new Healing1());
    }

    @Override
    public Healing addHealing2() {
        return this.addHealing(new Healing2());
    }

    @Override
    public Healing addHealing3() {
        return this.addHealing(new Healing3());
    }


    @Override
    public Weapone addWeapone1() {
        return this.addWeapone(new Weapone1());
    }

    @Override
    public Weapone addWeapone2() {
        return this.addWeapone(new Weapone2());
    }

    @Override
    public Weapone addWeapone3() {
        return this.addWeapone(new Weapone3());
    }


    @Override
    public Defense addDefense(Defense p) {
        logger.info("new defense to add: " + p);

        this.items.add (p);
        logger.info("new Defense added");
        return p;
    }

    @Override
    public Healing addHealing(Healing t) {
        logger.info("new healing to add: " + t);

        this.items.add (t);
        logger.info("new Healing added");
        return t;
    }

    @Override
    public Weapone addWeapone(Weapone t) {
        logger.info("new weapone to add: " + t);

        this.items.add (t);
        logger.info("new weapone added");
        return t;
    }

    @Override
    public Item getItem(String id) {
        logger.info("getItem(" + id + ")");

        for (Item t: this.items) {
            if (t.getId().equals(id)) {
                logger.info("getItem(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("items not found with this id: " + id);
        return null;
    }

    @Override
    public void deleteItem(String id) {
        logger.info("Want to delete Item with this id: " + id);
        Item t = this.getItem(id);

        if (t==null) { //Comprobamos que el Item existe
            logger.warn("Item not found " + t); //No creo que pueda pasar
        }
        else this.items.remove(t);
        logger.info(t + "Item deleted");
    }

    @Override
    public List<Item> findAll() {
        return this.items;
    }
}
