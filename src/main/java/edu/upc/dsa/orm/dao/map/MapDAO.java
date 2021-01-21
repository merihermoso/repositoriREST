package edu.upc.dsa.orm.dao.map;

import edu.upc.dsa.orm.models.Map;

import java.util.List;

public interface MapDAO {

      // CRUD Functions (Create, Read, Update and Delete)

      // CREATE
      boolean create(Map map);

      // READ
      List<Map> readAll();
      List<Map> readAllByParameter(String byParameter, Object byParameterValue);
      Map readByParameter(String byParameter, Object byParameterValue);
      Object readParameterByParameter(String parameter, String byParameter, Object byParameterValue);


      boolean existsId(int id);
      // UPDATE
      boolean update(Map map);
      boolean updateByParameter(Map map, String byParameter, Object byParameterValue);
      boolean updateParameterByParameter(String parameter, Object parameterValue
              , String byParameter, Object byParameterValue);


      // DELETE
      boolean delete(Map map);
      boolean deleteByParameter(String byParameter, Object byParameterValue);


}
