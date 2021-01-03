package edu.upc.dsa.orm;

import edu.upc.dsa.*;
import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.util.ObjectHelper;
import edu.upc.dsa.orm.util.QueryHelper;


import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;


public class SessionImpl implements edu.upc.dsa.orm.Session {

    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);

        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {

    }

    public HashMap<Integer, Object> findAll(Class theClass) {

        HashMap<Integer, Object> result = new HashMap<>();

        String selectQuery = QueryHelper.createQuerySELECTAll(theClass);

        PreparedStatement pstm;
        ResultSet resultSet;
        Object object;
        int id;

        try {

            object = theClass.getDeclaredConstructor().newInstance();
            pstm = conn.prepareStatement(selectQuery);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {

                ResultSetMetaData rsmd = resultSet.getMetaData();

                for (int i = 1; i<=rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, resultSet.getObject(i));
                }

                result.put((int) resultSet.getObject(1), object);
                object = theClass.getDeclaredConstructor().newInstance();

            }

        } catch (SQLException sqlException) {

            sqlException.printStackTrace();

        } catch (NoSuchMethodException noSuchMethodException) {

            noSuchMethodException.printStackTrace();

        } catch (IllegalAccessException illegalAccessException) {

            illegalAccessException.printStackTrace();

        } catch (InstantiationException instantiationException) {

            instantiationException.printStackTrace();

        } catch (InvocationTargetException invocationTargetException) {

            invocationTargetException.printStackTrace();

        }

        return result;

    }

    public boolean registerUser(RegisterCredentials registerCredentials) {

        User user = new User(registerCredentials.getUsername(), registerCredentials.getEmail(), registerCredentials.getPassword(), registerCredentials.getBirthdate());

        String insertQuery = QueryHelper.createQueryINSERT(user);

        PreparedStatement pstm;

        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field: ObjectHelper.getFields(user)) {
                pstm.setObject(i, ObjectHelper.getter(user, field));
                i++;
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

    public boolean loginUser(LoginCredentials loginCredentials) {

        String selectQuery = QueryHelper.createQueryUserSELECTPasswordByUsername();

        PreparedStatement pstm;
        ResultSet resultSet;

        try {

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, loginCredentials.getUsername());
            resultSet = pstm.executeQuery();

            if (resultSet.next()) {

                return resultSet.getString(1).equals(loginCredentials.getPassword());

            } else {

                return false;

            }

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

    public boolean userExists(String username) {

        String selectQuery = QueryHelper.createQueryUserSELECTbyUsername(username);

        PreparedStatement pstm;
        ResultSet resultSet;

        try {

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, username);
            resultSet = pstm.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

}