package edu.upc.dsa.orm;

import com.sun.tools.javac.util.Convert;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;
import edu.upc.dsa.orm.util.ObjectHelper;
import edu.upc.dsa.orm.util.QueryHelper;


import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

            for (String field : ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));       // guarda en el objeto de esa entidad y valor
            }

            pstm.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //Funcion que actualiza los datos de un objeto con los atributos
    //del objeto que le enviamos como parametro
    public void update(Object object) {
        String updateQuery = QueryHelper.createQueryUPDATE(object);
        PreparedStatement statement = null;
        int i = 1;
        try{
            statement = conn.prepareStatement(updateQuery);
            String[] fields = ObjectHelper.getFields(object);
            for(String field : fields){
                /*if(object.getClass()== Inventario.class) {
                    switch (field) {
                        case "cantidad":
                            statement.setObject(1, ObjectHelper.getter(object, field));
                            break;
                        case "idObjeto":
                            statement.setObject(2, ObjectHelper.getter(object, field));
                            break;
                        case "idJugador":
                            statement.setObject(3, ObjectHelper.getter(object, field));
                            break;
                    }
                }
                else{*/
                    statement.setObject(i, ObjectHelper.getter(object, field));
                    i++;
             //   }
            }
            System.out.println(statement);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        System.out.println("Object updated : "+object.toString());
    }

    //Funcion que busca a un objeto y lo elimina
    public void delete(Object object) {
        String deleteQuery = QueryHelper.createQueryDELETE(object);
        PreparedStatement statement = null;
        String idValue = null;
        try{
            /*if(object.getClass()==Inventario.class){
                statement = conn.prepareStatement("DELETE FROM Inventario WHERE idObjeto=? AND idJugador=?");
                statement.setObject(1, ObjectHelper.getter(object, "idObjeto"));
                statement.setObject(2, ObjectHelper.getter(object, "idJugador"));
            } else {*/
                statement = conn.prepareStatement(deleteQuery);
                idValue = (String) ObjectHelper.getter(object, "id" + object.getClass().getSimpleName());
                statement.setObject(1, ObjectHelper.getter(object, "id" + object.getClass().getSimpleName()));
        //    }
            statement.executeQuery();
            System.out.println(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
    public void close() {

    }
/*********************************      CONSULTES llistats      *************************************************/
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

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
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

        String selectQuery = QueryHelper.createQuerySELECTtop20(theClass);

        PreparedStatement pstm;
        ResultSet resultSet;
        Object object;

        int id;
        System.out.println(selectQuery);

        try {

            object = theClass.getDeclaredConstructor().newInstance();
            pstm = conn.prepareStatement(selectQuery);
            resultSet = pstm.executeQuery();

            int pos = 1;
            while (resultSet.next()) {

                ResultSetMetaData rsmd = resultSet.getMetaData();

                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object, field, resultSet.getObject(i));
                }
                result.put((int) pos, object);
                object = theClass.getDeclaredConstructor().newInstance();
                pos++;
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

/**********************************     AUTENTICACIONS      *************************************************/
    public boolean registerUser(RegisterCredentials registerCredentials) {

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(registerCredentials.getPassword().getBytes(StandardCharsets.UTF_8));

            User user = new User(registerCredentials.getUsername(), registerCredentials.getEmail(), encodeHex(hash), registerCredentials.getBirthdate_day() + "/" + registerCredentials.getBirthdate_month() + "/" + registerCredentials.getBirthdate_year());

            String insertQuery = QueryHelper.createQueryINSERT(user);

            PreparedStatement pstm;
            System.out.println(insertQuery);


            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field : ObjectHelper.getFields(user)) {
                pstm.setObject(i, ObjectHelper.getter(user, field));
                i++;
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean loginUser(LoginCredentials loginCredentials) {

        try {

            String selectQuery = QueryHelper.createQueryUserSELECTPasswordByUsername();

            PreparedStatement pstm;
            ResultSet resultSet;

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(loginCredentials.getPassword().getBytes(StandardCharsets.UTF_8));

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, loginCredentials.getUsername());
            resultSet = pstm.executeQuery();

            if (resultSet.next()) {


                return resultSet.getString(1).equals(encodeHex(hash));

            } else {

                return false;

            }

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        } catch (NoSuchAlgorithmException e) {

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
/*****************************************      CONSULTES  obtenim objectes     ***********************************************/

    public Object getById(Object theObject, int id) throws SQLException {
        String selectQuery = QueryHelper.createQuerySELECTbyID(theObject);          //quary que busca a partir del ID
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, id);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(theObject, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return theObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getByName(Object theObject, String name) throws SQLException {
        String selectQuery = QueryHelper.createQuerySELECTbyName(theObject);         //consulta per obtenir Partida del Username que introduim
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, name);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(theObject, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return theObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /************************************   OBTENIM ID A PARTIR DE NAME/USERNAME  *****************************/
//Función que devuelve el ID de un objeto
/*
    @Override
    public String getIDbyName(Class theClass, String name) {
        ResultSet rs;
        Object object = null;

        String selectID = QueryHelper.createQueryGetIDbyName(theClass);

        PreparedStatement pstm;

        try {
            object = theClass.newInstance();
            pstm = this.conn.prepareStatement(selectID);
            pstm.setObject(1, name);
            rs = pstm.executeQuery();
            System.out.println(pstm);
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i=1; i<=rsmd.getColumnCount();i++){
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object,field,rs.getObject(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Object founded: "+object);
        return null;
    }

 //   @Override
    public String getIDbyUsername(Class theClass, String username) {
        ResultSet rs;
        Object object = null;

        String selectID = QueryHelper.createQueryGetIDbyUsername(theClass);

        PreparedStatement pstm;

        try {
            object = theClass.newInstance();
            pstm = this.conn.prepareStatement(selectID);
            pstm.setObject(1, username);
            rs = pstm.executeQuery();
            System.out.println(pstm);
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for(int i=1; i<=rsmd.getColumnCount();i++){
                    String field = rsmd.getColumnName(i);
                    ObjectHelper.setter(object,field,rs.getObject(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("User founded: "+object);
        return null;
    }*/

    /************************************   OBTENIM OBJECTE A PARTIR DE USERNAME USER   *****************************/

    public Object getUserByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryUserSELECTbyUsername(username);
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, username);
            pstm.executeQuery();
            ResultSet rs = pstm.getResultSet();
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(theObject, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return theObject;

        } catch (Exception e) {
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
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(theObject, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return theObject;

        } catch (Exception e) {
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
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(theObject, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return theObject;

        } catch (Exception e) {
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
            if (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    ObjectHelper.setter(theObject, rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return theObject;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    /**********************************************   REGISTERS   ************************************************/
    public boolean registerOrder(OrderCredentials orderCredentials) {

        Orders orders = new Orders(orderCredentials.getDate(), orderCredentials.getTime(), orderCredentials.getPrice());

        String insertQuery = QueryHelper.createQueryINSERT(orders);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field : ObjectHelper.getFields(orders)) {
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

    public int getUserPositionByUsername(String username) {

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

        } catch (SQLException e) {

            e.printStackTrace();
            return -1;

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

            for (String field : ObjectHelper.getFields(element)) {
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

        Game game = new Game(gameCredentials.getDateStart(), gameCredentials.getTimeStart(), gameCredentials.getDateEnd(), gameCredentials.getTimeEnd(), gameCredentials.getScore());

        String insertQuery = QueryHelper.createQueryINSERT(game);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field : ObjectHelper.getFields(game)) {
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

        Item item = new Item(itemCredentials.getName(), itemCredentials.getHit(), itemCredentials.getDefense(), itemCredentials.getHealing(), itemCredentials.getDamage());

        String insertQuery = QueryHelper.createQueryINSERT(item);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field : ObjectHelper.getFields(item)) {
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

    public boolean registerEnemy(EnemyCredentials enemyCredentials) {

        Enemy enemy = new Enemy(enemyCredentials.getName(), enemyCredentials.getHit(),enemyCredentials.getHealing(),enemyCredentials.getDamage(),enemyCredentials.getDefense());

        String insertQuery = QueryHelper.createQueryINSERT(enemy);

        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {

            pstm = conn.prepareStatement(insertQuery);

            int i = 1;

            for (String field : ObjectHelper.getFields(enemy)) {
                pstm.setObject(i, ObjectHelper.getter(enemy, field));
                i++;
            }

            pstm.executeQuery();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }

    }
    /****************************************************************************************************************/

    private static String encodeHex(byte[] digest) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }


}

