package edu.upc.dsa.orm.dao.entity;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Entity;
import edu.upc.dsa.orm.models.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityDAOImpl implements EntityDAO {

    private static EntityDAO instance;
    private final Session session;

    private EntityDAOImpl() {
        session = FactorySession.openSession();
    }

    public static EntityDAO getInstance() {
        if (instance==null) instance = new EntityDAOImpl();
        return instance;
    }
    /*****************************************  FUNCIONS GENERALS    ***************************************************/

    public boolean create(Entity entity) {

        return session.create(entity);

    }


    public boolean exists(String name) {

        return (session.readByParameter(Item.class, "name", name) != null);

    }


    public boolean existsId(int id) {

        return (session.readByParameter(Item.class, "id", id) != null);

    }

    // READ
    public List<Entity> readAll(){

        Session session;
        List<Entity> entityList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Entity.class);

        entityList = new ArrayList<>();

        for (Object object : result.values()) {
            entityList.add((Entity) object);
        }

        session.close();

        return entityList;
    }


    public Entity readByParameter(String byParameter, Object byParameterValue) {

        return ((Entity) session.readByParameter(Entity.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

        return session.readParameterByParameter(Entity.class, parameter, byParameter, byParameterValue);

    }



    // UPDATE

    public boolean update(Entity entity) {

        return session.update(entity);

    }

    public boolean updateByParameter(Entity entity, String byParameter, Object byParameterValue) {

        return session.updateByParameter(entity, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Entity.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Entity entity) {

        return session.delete(entity);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Entity.class, byParameter, byParameterValue);

    }
}
