package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.Enemy1;
import org.apache.log4j.Logger;

public class Enemies1ManagerImpl implements Enemies1Manager {
    private static Enemies1Manager instance;

    protected List<Enemy1> enemies1; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(UsersManagerImpl.class);

    private Enemies1ManagerImpl() { //Constructor
        this.enemies1 = new LinkedList<>();
    }

    public static Enemies1Manager getInstance() {
        if (instance==null) instance = new Enemies1ManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.enemies1.size();
        logger.info("size users =" + ret);

        return ret;
    }

    public Enemy1 addEnemy1(int x, int y) {
        return this.addEnemy1(new Enemy1(x, y));
    }

    public Enemy1 addEnemy1(Enemy1 t) {
        logger.info("new user to add: " + t);

        this.enemies1.add (t);
        logger.info("new Enemy1 added");
        return t;
    }

    public Enemy1 getEnemy1(String id) { //Recogemos el objeto "Enemy1" atraves del "id"
        logger.info("getEnemy(" + id + ")");

        for (Enemy1 t: this.enemies1) {
            if (t.getId().equals(id)) {
                logger.info("getEnemy(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("enemy not found with this id: " + id);
        return null;
    }

    public List<Enemy1> findAll() {
        return this.enemies1;
    }

    public void deleteEnemy1(String id) { //Matamos a un enemigo tipo 1
        logger.info("Want to delete enemy with this id: " + id);
        Enemy1 t = this.getEnemy1(id);
        if (t==null) { //Comprobamos que el enemigo existe
            logger.warn("enemy not found " + t); //No creo que pueda pasar
        }
        else this.enemies1.remove(t);
        logger.info(t + "Enemy1 deleted");
    }
}
