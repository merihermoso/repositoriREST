package edu.upc.dsa;

import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.Enemy1;
import org.apache.log4j.Logger;

public class EnemyManagerImpl implements EnemyManager{
    private static UsersManager instance;

    protected List<Enemy1> enemies1; //Creamos la lista de enemigos de tipo 1

    final static Logger logger = Logger.getLogger(UsersManagerImpl.class);

    private EnemyManagerImpl() { //Constructor
        this.enemies1 = new LinkedList<>();
    }

    public Enemy1 getEnemy1(String id) { //Recogemos el objeto "Enemy1" atraves del "id"
        logger.info("getEnemy(" + id + ")");

        for (Enemy1 t: this.enemies1) {
            if (t.getId().equals(id)) {
                logger.info("getEnemy(" + id + "): " + t);

                return t;
            }
        }

        logger.warn("user not found with this id: " + id);
        return null;
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
