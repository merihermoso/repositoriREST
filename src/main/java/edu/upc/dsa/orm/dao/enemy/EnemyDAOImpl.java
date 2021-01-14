package edu.upc.dsa.orm.dao.enemy;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EnemyDAOImpl implements EnemyDAO {

    private static EnemyDAO instance;
    private final Session session;

    private EnemyDAOImpl() {
        session = FactorySession.openSession();
    }

    public static EnemyDAO getInstance() {
        if (instance==null) instance = new EnemyDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/

    public boolean create(Enemy enemy) {

        return session.create(enemy);

    }

    // READ
    public List<Enemy> readAll(){

        Session session;
        List<Enemy> enemyList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Enemy.class);

        enemyList = new ArrayList<>();

        for (Object object : result.values()) {
            enemyList.add((Enemy) object);
        }

        session.close();

        return enemyList;
    }


    public Enemy readByParameter(String byParameter, Object byParameterValue) {

        return ((Enemy) session.readByParameter(Enemy.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(Enemy.class, parameter, byParameter, byParameterValue);

    }



    // UPDATE

    public boolean update(Enemy enemy) {

        return session.update(enemy);

    }

    public boolean updateByParameter(Enemy enemy, String byParameter, Object byParameterValue) {

        return session.updateByParameter(enemy, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Enemy.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Enemy enemy) {

        return session.delete(enemy);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Enemy.class, byParameter, byParameterValue);

    }
}
