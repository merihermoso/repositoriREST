package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.Enemy.*;
import org.apache.log4j.Logger;

public class EnemiesManagerImpl implements EnemiesManager {
    private static EnemiesManager instance;

    protected List<Enemy> enemies; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(UsersManagerImpl.class);

    private EnemiesManagerImpl() { //Constructor
        this.enemies = new LinkedList<>();
    }

    public static EnemiesManager getInstance() {
        if (instance==null) instance = new EnemiesManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.enemies.size();
        logger.info("size users =" + ret);

        return ret;
    }

    public Enemy addEnemy1(int x, int y) //Añadimos un enemigo de tipo 1 a la lista
    {
        return this.addEnemy(new Enemy1(x, y));
    }

    public Enemy addEnemy2(int x, int y) //Añadimos un enemigo de tipo 2 a la lista
    {
        return this.addEnemy(new Enemy2(x, y));
    }

    public Enemy addEnemy3(int x, int y) //Añadimos un enemigo de tipo 3 a la lista
    {
        return this.addEnemy(new Enemy3(x, y));
    }

    public Enemy addEnemy(Enemy t) //Creamos el enemigo
    {
        logger.info("new user to add: " + t);

        this.enemies.add (t);
        logger.info("new Enemy added");
        return t;
    }

    public Enemy getEnemy(String id) //Recogemos el objeto "Enemy" atraves del "id"
    {
        logger.info("getEnemy(" + id + ")");

        for (Enemy t: this.enemies) {
            if (t.getId().equals(id)) {
                logger.info("getEnemy(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("enemy not found with this id: " + id);
        return null;
    }

    public List<Enemy> findAll() //Listamos los enemigos
    {
        return this.enemies;
    }

    public void deleteEnemy(String id) //Matamos a un enemigo
    {
        logger.info("Want to delete enemy with this id: " + id);
        Enemy t = this.getEnemy(id);

        if (t==null) { //Comprobamos que el enemigo existe
            logger.warn("enemy not found " + t); //No creo que pueda pasar
        }
        else this.enemies.remove(t);
        logger.info(t + "Enemy deleted");
    }
}
