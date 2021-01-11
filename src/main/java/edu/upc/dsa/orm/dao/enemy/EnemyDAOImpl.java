package edu.upc.dsa.orm.dao.enemy;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Enemy;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnemyDAOImpl implements EnemyDAO {
    private static EnemyDAO instance;

    private EnemyDAOImpl() {
    }

    public static EnemyDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new EnemyDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/

    public List<Enemy> findAll(){

        Session session;
        List<Enemy> enemyList;

        HashMap<Integer, Enemy> result;

        session = FactorySession.openSession();
        result = session.findAll(Enemy.class);

        enemyList = new ArrayList<>(result.values());

        session.close();

        return enemyList;
    }
    public int size() {
        Session session;
        HashMap<String, Enemy> enemies = null;
        try{
            session = FactorySession.openSession();
            enemies = session.findAll(Enemy.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enemies.size();
    }
    /***************************************** GET ***********************************************/


    public Enemy getEnemyById(int enemyID) throws SQLException {
        Session session = null;
        Enemy enemy = new Enemy();
        try {
            session = FactorySession.openSession();
            enemy = (Enemy) session.getById(enemy, enemyID);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return enemy;
    }

    public Enemy getEnemyByName(String name) throws SQLException {
        Session session = null;
        Enemy enemy = new Enemy();
        try {
            session = FactorySession.openSession();
            enemy = (Enemy) session.getByName(enemy, name);          //com poso la relació game User?¿
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return enemy;
    }

    /*********************************** REGISTRE ENEMICS *******************************************/
    public boolean registerEnemy(EnemyCredentials enemyCredentials) throws IllegalAccessException { //Afegeix el user com a obejcte

        Session session;
        boolean result = false;

        try {

            session = FactorySession.openSession();
            result = session.registerEnemy(enemyCredentials);
            session.close();

        } finally {

        }

        return result;

    }

    public int updateEnemy(Enemy enemy) throws SQLException {

        Session session = null;
        int res=1;

        try {
            session = FactorySession.openSession();
            session.update(enemy);
            res =0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            return res;
        }
    }

    /**********************************************************************************************************/

    /*
    public int deleteEnemy(Enemy enemy) throws SQLException {
        Session session = null;
        int res=1;
        try {
            session = FactorySession.openSession();
            session.delete(enemy);
            res =0;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
            return res;
        }
    }*/
}
