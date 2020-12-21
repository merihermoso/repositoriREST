package edu.upc.dsa;


import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Defense.*;
import edu.upc.dsa.models.Healing.*;
import edu.upc.dsa.models.Weapone.*;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class InventoryManagerImpl implements InventoryManager {
    private static InventoryManager instance;

    protected List<Item> items; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(InventoryManagerImpl.class);

    private InventoryManagerImpl() { //Constructor
        this.items = new LinkedList<>();
    }

    public static InventoryManager getInstance() {
        if (instance==null) instance = new InventoryManagerImpl();
        return instance;
    }


    public int size() {
        int ret = this.items.size();
        logger.info("size items =" + ret);

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


    /*@Override
    public Defense getDefense(String id) {
        logger.info("getDefense(" + id + ")");

        for (Defense t: this.items) {
            if (t.getId().equals(id)) {
                logger.info("getDefenses(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("defenses not found with this id: " + id);
        return null;
    }

    @Override
    public Healing getHealing(String id) {
        logger.info("getHealing(" + id + ")");

        for (Healing t: this.items) {
            if (t.getId().equals(id)) {
                logger.info("getHealing(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("healing not found with this id: " + id);
        return null;
    }




    @Override
    public Weapone getWeapone(String id) {
        logger.info("getWeapone(" + id + ")");

        for (Weapone t: this.items) {
            if (t.getId().equals(id)) {
                logger.info("getWeapone(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("weapone not found with this id: " + id);
        return null;
    }


    @Override
    public void deleteDefense(String id) {
        logger.info("Want to delete Item with this id: " + id);
        Defense t = this.getDefense(id);

        if (t==null) { //Comprobamos que el Item existe
            logger.warn("Item not found " + t); //No creo que pueda pasar
        }
        else this.items.remove(t);
        logger.info(t + "Item deleted");
    }

    @Override
    public void deleteHealing(String id) {
        logger.info("Want to delete Item with this id: " + id);
        Healing t = this.getHealing(id);

        if (t==null) { //Comprobamos que el Item
            logger.warn("Item not found " + t); //No creo que pueda pasar
        }
        else this.items.remove(t);
        logger.info(t + "Item deleted");
    }

    @Override
    public void deleteWeapone(String id) {
        logger.info("Want to delete Item with this id: " + id);
        Weapone t = this.getWeapone(id);

        if (t==null) { //Comprobamos que la Item
            logger.warn("Item not found " + t); //No creo que pueda pasar
        }
        else this.items.remove(t);
        logger.info(t + "Item deleted");
    }*/
}
