package edu.upc.dsa.orm.dao.map;

import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.Map;
import edu.upc.dsa.orm.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapDAOImpl implements MapDAO {
    private static MapDAO instance;
    private final Session session;

    private MapDAOImpl() {

        session = FactorySession.openSession();

    }

    public static MapDAO getInstance() {                    //DA ERROR
        if (instance==null) instance = new MapDAOImpl();
        return instance;
    }



    public boolean create(Map map) {

        return session.create(map);

    }

    public boolean existsId(int id) {

        return (session.readByParameter(User.class, "id", id) != null);

    }

    // READ
    public List<Map> readAll(){

        Session session;
        List<Map> mapList;

        HashMap<Integer, Object> result;

        session = FactorySession.openSession();
        result = session.readAll(Map.class);

        mapList = new ArrayList<>();

        for (Object object : result.values()) {
            mapList.add((Map) object);
        }

        session.close();

        return mapList;
    }


    public List<Map> readAllByParameter(String byParameter, Object byParameterValue){

        List<Map> mapList;

        HashMap<Integer, Object> result;

        result = session.readAllByParameter(Map.class, byParameter, byParameterValue);

        mapList = new ArrayList<>();

        for (Object object : result.values()) {
            mapList.add((Map) object);
        }

        return mapList;
    }


    public Map readByParameter(String byParameter, Object byParameterValue) {

        return ((Map) session.readByParameter(Map.class, byParameter, byParameterValue));

    }

    public Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue) {

       return session.readParameterByParameter(Map.class, parameter, byParameter, byParameterValue);

    }


    // UPDATE

    public boolean update(Map map) {

        return session.update(map);

    }

    public boolean updateByParameter(Map map, String byParameter, Object byParameterValue) {

        return session.updateByParameter(map, byParameter, byParameterValue);

    }

    public boolean updateParameterByParameter(String parameter, Object parameterValue
            , String byParameter, Object byParameterValue) {

        return session.updateParameterByParameter(Map.class, parameter, parameterValue, byParameter, byParameterValue);

    }


    public boolean delete(Map map) {

        return session.delete(map);

    }

    public boolean deleteByParameter(String byParameter, Object byParameterValue) {

        return session.deleteByParameter(Map.class, byParameter, byParameterValue);

    }


}
