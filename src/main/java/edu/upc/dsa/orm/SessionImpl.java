package edu.upc.dsa.orm;

import edu.upc.dsa.orm.exeptions.UserNotFoundException;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.util.ObjectHelper;
import edu.upc.dsa.orm.util.QueryHelper;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;


public class SessionImpl implements Session {

    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void close() {

    }

    // CRUD database functions (Create, Read, Update, Delete)

    public boolean create(Object object) {

        try {

            String insertQuery = QueryHelper.createQueryINSERT(object.getClass());
            PreparedStatement pstm = null;

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;
            for (String field : ObjectHelper.getFields(object)) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            return false;

        }

    }

    public boolean update(Object object) {

        try {

            String updateQuery = QueryHelper.createQueryUPDATE(object);

            PreparedStatement pstm = null;

            pstm = conn.prepareStatement(updateQuery);
            String field;
            int i = 1;
            while (i < ObjectHelper.getFields(object).length) {
                field = ObjectHelper.getFields(object)[i];
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.setObject(i++, ObjectHelper.getter(object, ObjectHelper.getFields(object)[0]));
            pstm.executeQuery();

            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            return false;

        }

    }

    public boolean updateByParameter(Object object, String byParameter, Object byParameterValue) {

        try {

            String updateQuery = QueryHelper.createQueryUPDATEbyParameter(object.getClass(), byParameter);

            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(updateQuery);
            String field;
            int i = 1;

            while (i < ObjectHelper.getFields(object).length) {
                field = ObjectHelper.getFields(object)[i];
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }

            pstm.setObject(i++, ObjectHelper.getter(object, byParameterValue.toString()));
            pstm.executeQuery();

            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            return false;

        }

    }


    public boolean updateParameterByParameter(Class theClass, String parameter, Object parameterValue, String byParameter, Object byParameterValue) {

        try {

            String updateQuery = QueryHelper.createQueryUPDATEparameterByParameter(theClass, parameter, byParameter);

            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(updateQuery);
            pstm.setObject(1, parameterValue);
            pstm.setObject(2, byParameterValue);
            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            return false;

        }

    }


    public HashMap<Integer, Object> readAll(Class theClass) {           //obtener todos (aplicable a todas las funciones)

        try {

            HashMap<Integer, Object> result = new HashMap<>();
            String selectQuery = QueryHelper.createQuerySELECTall(theClass);
            PreparedStatement pstm;
            ResultSet resultSet;
            Object object;
            int id;
            object = theClass.getDeclaredConstructor().newInstance();
            pstm = conn.prepareStatement(selectQuery);
            resultSet = pstm.executeQuery();
            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, resultSet.getObject(i));
                }
                result.put((int) resultSet.getObject(1), object);
                object = theClass.getDeclaredConstructor().newInstance();
            }

            return result;

        } catch (SQLException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException exception) {

            exception.printStackTrace();
            return null;

        }


    }

    public HashMap<Integer, User> readUserRanking(int limit) {

        HashMap<Integer, User> result = new HashMap<>();
        String selectQuery = QueryHelper.createQuerySELECTallExtras(User.class,"ORDER BY score DESC LIMIT " + limit);
        PreparedStatement pstm;
        ResultSet resultSet;

        try {

            User user;
            int id;
            user = new User();
            pstm = conn.prepareStatement(selectQuery);
            resultSet = pstm.executeQuery();
            int pos = 1;
            while (resultSet.next()) {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(user, field, resultSet.getObject(i));
                }
                result.put((int) pos, user);
                user = User.class.getDeclaredConstructor().newInstance();
                pos++;
            }

        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InvocationTargetException
                | InstantiationException exception) {

            exception.printStackTrace();

        }

        return result;

    }

    public Object readParameterByParameter(Class theClass, String parameter, String byParameter, Object byParameterValue) {

        try {
            String selectQuery = QueryHelper.createQuerySELECTparameterByParameter(theClass, parameter, byParameter);
            PreparedStatement pstm;
            ResultSet resultSet;
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, byParameterValue);
            resultSet = pstm.executeQuery();
            if (resultSet.next()) return resultSet.getObject(1);
            else return null;

        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }

    }


    public Object readByParameter(Class theClass, String byParameter, Object byParameterValue) {

        try {

            String selectQuery = QueryHelper.createQuerySELECTbyParameter(theClass, byParameter);
            PreparedStatement pstm = null;
            Object object = theClass.getDeclaredConstructor().newInstance();
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, byParameterValue);
            pstm.executeQuery();
            ResultSet resultSet = pstm.getResultSet();

            if (resultSet.next()) {

                ResultSetMetaData rsmd = resultSet.getMetaData();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, resultSet.getObject(i));
                }

                return object;

            } else {

                return null;

            }

        } catch (SQLException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException exception) {

            exception.printStackTrace();
            return null;

        }

    }

    public int readUserRankingPositionByUsername(String username) {

        try {

            String selectQuery = QueryHelper.createQueryUserPositionSELECTbyUsername();

            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, username);
            resultSet = pstm.executeQuery();

            if (resultSet.next()) {

                return resultSet.getInt(1);

            } else {

                return -1;

            }

        } catch (SQLException exception) {

            exception.printStackTrace();
            return -1;

        }

    }



    public boolean delete(Object object)  {

        try {

            String deleteQuery = QueryHelper.createQueryDELETE(object);
            PreparedStatement pstm = null;
            int id = ObjectHelper.getId(object);
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setInt(1, id);
            pstm.executeQuery();

            return true;

        } catch (SQLException | IllegalAccessException exception) {

            exception.printStackTrace();

            return false;

        }

    }

    public boolean deleteByParameter(Class theClass, String byParameter, Object byParameterValue)  {

        try {

            String deleteQuery = QueryHelper.createQueryDELETEbyParameter(theClass, byParameter);
            PreparedStatement pstm = null;
            pstm = conn.prepareStatement(deleteQuery);
            pstm.setObject(1, byParameterValue);
            pstm.executeQuery();

            return true;

        } catch (SQLException exception) {

            exception.printStackTrace();
            return false;

        }

    }


}

