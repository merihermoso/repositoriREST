package edu.upc.dsa.orm;

import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;
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

    public void save(Object entity) {                   //guardar

        String insertQuery = QueryHelper.createQueryINSERT(entity);         //INSERTA
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            pstm.setObject(1, 0);
            int i = 2;

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));       // guarda en el objeto de esa entidad y valor
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void close() {

    }

    public HashMap<Integer, Object> findAll(Class theClass) {           //obtener todos (aplicable a todas las funciones)

        HashMap<Integer, Object> result = new HashMap<>();

        String selectQuery = QueryHelper.createQuerySELECTAll(theClass);

        PreparedStatement pstm;
        ResultSet resultSet;
        Object object;
        int id;
        System.out.println(selectQuery);
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

    public HashMap<Integer, Object> findTop(Class theClass) {           //findAll ordenado BY score

        HashMap<Integer, Object> result = new HashMap<>();

        String selectQuery = QueryHelper.createQuerySELECTtop(theClass);

        PreparedStatement pstm;
        ResultSet resultSet;
        Object object;
        int id;
        System.out.println(selectQuery);
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
        System.out.println(insertQuery);
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

    public boolean userExists(String username) {            //busca per username i retorna true si existeix

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

    public Object getById(Object theObject, int id) throws SQLException {
        String selectQuery = QueryHelper.createQuerySELECT(theObject);          //quary que busca a partir del ID
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, id);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getUserByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryUserSELECTbyUsername(username);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getGameByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryGameSELECTbyUsername(username);         //consulta per obtenir Partida del Username que introduim
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getOrderByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryOrderSELECTbyUsername(username);         //consulta per obtenir Partida del Username que introduim
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getElementByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryElementSELECTbyUsername(username);         //consulta per obtenir Partida del Username que introduim
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()){
                for (int i=1;i<=rs.getMetaData().getColumnCount();i++)
                    ObjectHelper.setter(theObject,rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return theObject;

        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //////////////////////////////////////////////////////////////////////////   REGISTERS   ////////////////
    public boolean registerOrder(OrderCredentials orderCredentials) {

        Orders orders = new Orders(orderCredentials.getDate(), orderCredentials.getTime(), orderCredentials.getPrice());

        String insertQuery = QueryHelper.createQueryINSERT(orders);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field: ObjectHelper.getFields(orders)) {
                pstm.setObject(i, ObjectHelper.getter(orders, field));
                i++;
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }


   public boolean registerElement(ElementCredentials elementCredentials) {

       Element element = new Element(elementCredentials.getName(), elementCredentials.getDescription(), elementCredentials.getPrice());

       String insertQuery = QueryHelper.createQueryINSERT(element);

       PreparedStatement pstm;
       System.out.println(insertQuery);
       try {

           pstm = conn.prepareStatement(insertQuery);

           int i = 1;

           for (String field: ObjectHelper.getFields(element)) {
               pstm.setObject(i, ObjectHelper.getter(element, field));
               i++;
           }

           pstm.executeQuery();

           return true;

       } catch (SQLException e) {
           e.printStackTrace();

           return false;
       }

   }
    public boolean registerGame(GameCredentials gameCredentials) {

        Game game = new Game(gameCredentials.getDateStart(), gameCredentials.getTimeStart(),gameCredentials.getDateEnd(),gameCredentials.getTimeEnd(), gameCredentials.getScore());

        String insertQuery = QueryHelper.createQueryINSERT(game);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field: ObjectHelper.getFields(game)) {
                pstm.setObject(i, ObjectHelper.getter(game, field));
                i++;
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }
    public boolean registerItem(ItemCredentials itemCredentials) {

        Item item = new Item(itemCredentials.getName(),itemCredentials.getHit(), itemCredentials.getDefense(), itemCredentials.getHealing(), itemCredentials.getDamage());

        String insertQuery = QueryHelper.createQueryINSERT(item);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field: ObjectHelper.getFields(item)) {
                pstm.setObject(i, ObjectHelper.getter(item, field));
                i++;
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }

}