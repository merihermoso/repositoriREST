package edu.upc.dsa.orm;

import edu.upc.dsa.orm.exeptions.UserNotFoundException;
import edu.upc.dsa.orm.models.API.ChangeEmailCredentials;
import edu.upc.dsa.orm.models.API.ChangePasswordCredentials;
import edu.upc.dsa.orm.models.API.LoginCredentials;
import edu.upc.dsa.orm.models.API.RegisterCredentials;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.GameCredentials.PlayerCredentials;
import edu.upc.dsa.orm.models.adminCredentials.*;
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

    @Override                                           //Obtenir
    public void get(Object entity) {

    }

    public void save(Object entity) throws IllegalAccessException {                   //guardar

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


    public void update(Object object) throws SQLException, IllegalAccessException {
        String updateQuery = QueryHelper.createQueryUPDATE(object);

        PreparedStatement pstm = null;

        pstm = conn.prepareStatement(updateQuery);
        String field;
        int i =1;
        while (i<ObjectHelper.getFields(object).length){
            field = ObjectHelper.getFields(object)[i];
            pstm.setObject(i++, ObjectHelper.getter(object, field));
        }
        pstm.setObject(i++, ObjectHelper.getter(object, ObjectHelper.getFields(object)[0]));
        pstm.executeQuery();

    }

    //Funcion que busca a un objeto y lo elimina
    public void delete(Object object) throws IllegalAccessException {
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

    //Funcion que devuelve los objetos de un jugador
    public HashMap<Integer, Inventory> getItemsUser(Class theClass, int id_user) throws UserNotFoundException {
        String objetosQuery = "SELECT * FROM Inventory WHERE id_user='"+id_user+"'";
        HashMap<Integer, Inventory> res = new HashMap<>();
        ResultSet rs;
        Object object;
        Integer id = null;
        Statement statement = null;

        try {
            object = theClass.getDeclaredConstructor().newInstance();
            statement = this.conn.createStatement();
            statement.execute(objetosQuery);
            rs = statement.getResultSet();
            System.out.println(rs);

            //Obtenemos los objetos y leemos las columnas con metadata
            //para ir guardando en cada objeto sus datos correspondientes
            while(rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i=1; i<=rsmd.getColumnCount(); i++) {
                    String field = rsmd.getColumnName(i);
                    System.out.println(object);
                    ObjectHelper.setter(object, field, rs.getObject(i));
                    if(i==1) id = (Integer) rs.getObject(i);
                }
                res.put(id, (Inventory) object);
                object = theClass.getDeclaredConstructor().newInstance();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //Devolvemos la lista con los objetos encontrados
        return res;
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

    //Funció que retorna els coins de un PLAYER
    @Override
    public int getCoinsPlayer(String id_player) {
        ResultSet rs;
        int coins = 0;
        Statement statement;
        String selectQuery = "SELECT coins FROM Player WHERE id_player='"+id_player+"'";
        try{
            statement = this.conn.createStatement();
            statement.execute(selectQuery);
            rs = statement.getResultSet();
            if(rs.next())
                coins = (int) rs.getObject(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coins;
    }

    //Funció que retorna el preu d'un Item
    public int getPriceItem(int id_item){
        ResultSet rs;
        int price = 0;
        Statement statement;
        String selectQuery = QueryHelper.createQuerySELECTPriceItem(id_item);
        try{
            statement = this.conn.createStatement();
            statement.execute(selectQuery);
            rs = statement.getResultSet();
            if (rs.next()) {
                price = (int) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }

    @Override
    public int getCoinsPlayer(int id_player) {
        ResultSet rs;
        int coins = 0;
        Statement statement;
        String selectQuery = QueryHelper.createQuerySELECTCoinsPlayer(id_player);
        try{
            statement = this.conn.createStatement();
            statement.execute(selectQuery);
            rs = statement.getResultSet();
            if (rs.next()) {
                coins = (int) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return coins;
    }


    public boolean changePassword(ChangePasswordCredentials changePasswordCredentials) {
        try {
            String selectQuery = QueryHelper.createQueryUPDATEPasswordByUsername();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(changePasswordCredentials.getNewPassword().getBytes(StandardCharsets.UTF_8));
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, encodeHex(hash));
            pstm.setString(2, changePasswordCredentials.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;

        }

    }

    public boolean changeEmail(ChangeEmailCredentials changeEmailCredentials) {
        try {
            String selectQuery = QueryHelper.createQueryUPDATEEmailByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, changeEmailCredentials.getNewEmail());
            pstm.setString(2, changeEmailCredentials.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeStatus(ChangeStatus changeStatusCredentials) {
        try {
            String selectQuery = QueryHelper.createQueryUPDATEStatusByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, changeStatusCredentials.getNewStatus());
            pstm.setString(2, changeStatusCredentials.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeScore(ChangeScore changeScoreCredentials) {
        try {
            String selectQuery = QueryHelper.createQueryUPDATEScoreByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setInt(1, changeScoreCredentials.getNewScore());
            pstm.setString(2, changeScoreCredentials.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeLevel(ChangeLevel changeLevelCredentials) {
        try {
            String selectQuery = QueryHelper.createQueryUPDATELevelByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setInt(1, changeLevelCredentials.getNewLevel());
            pstm.setString(2, changeLevelCredentials.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

/*****************************          modificacions PLAYER                *******************************************/

    public boolean changePlayerLevel(ChangePlayerLevel changePlayerLevel) {
        try {
            String selectQuery = QueryHelper.createQueryPlayerUPDATELevelByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setInt(1, changePlayerLevel.getNewLevel());
            pstm.setString(2, changePlayerLevel.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean changePlayerStatus(ChangePlayerStatus changePlayerStatus) {
        try {
            String selectQuery = QueryHelper.createQueryPlayerUPDATEStatusByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, changePlayerStatus.getNewStatus());
            pstm.setString(2, changePlayerStatus.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean changePlayerCoins(ChangePlayerCoins changePlayerCoins) {
        try {
            String selectQuery = QueryHelper.createQueryPlayerUPDATECoinsByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setInt(1, changePlayerCoins.getNewCoins());
            pstm.setString(2, changePlayerCoins.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean changePlayerSpeed(ChangePlayerSpeed changePlayerSpeed) {
        try {
            String selectQuery = QueryHelper.createQueryPlayerUPDATESpeedByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setInt(1, changePlayerSpeed.getNewSpeed());
            pstm.setString(2, changePlayerSpeed.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changePlayerScore(ChangePlayerScore changePlayerScore) {
        try {
            String selectQuery = QueryHelper.createQueryPlayerUPDATEScoreByUsername();
            System.out.println(selectQuery);
            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setInt(1, changePlayerScore.getNewScore());
            pstm.setString(2, changePlayerScore.getUsername());
            resultSet = pstm.executeQuery();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
/**********************************     AUTENTICACIONS      *************************************************/
    public boolean registerUser(RegisterCredentials registerCredentials) throws IllegalAccessException {
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
//Función para obtener el id del elemento a partir de su nombre
    public int getItemIdByName(String name) {
        try {
            String selectQuery = QueryHelper.createQueryItemIdSELECTbyName();

            PreparedStatement pstm;
            ResultSet resultSet;

            pstm = conn.prepareStatement(selectQuery);
            pstm.setString(1, name);
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
    public int getUserIdByUsername(String username) {
        try {
            String selectQuery = QueryHelper.createQueryIdSELECTbyUsername();

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

    public Object getPlayerByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryPlayerSELECTbyUsername(username);
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
    public Object getItemByUsername(Object theObject, String username) throws SQLException {
        String selectQuery = QueryHelper.createQueryItemSELECTbyUsername(username);
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



    //Registrar nuevo Player
    public boolean registerPlayer(PlayerCredentials playerCredentials) throws IllegalAccessException {
        Player player = new Player(playerCredentials.getStatus(),playerCredentials.getCoins(),playerCredentials.getScore(), playerCredentials.getNumLevel(), playerCredentials.getSpeed(), playerCredentials.getHit(), playerCredentials.getDefense(), playerCredentials.getHealing(), playerCredentials.getDamage());
        String insertQuery = QueryHelper.createQueryINSERT(player);
        PreparedStatement pstm;
        System.out.println(insertQuery);
        try {
            pstm = conn.prepareStatement(insertQuery);
            int i = 1;
            for (String field : ObjectHelper.getFields(player)) {
                pstm.setObject(i, ObjectHelper.getter(player, field));
                i++;
            }
            pstm.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerGame(GameCredentials gameCredentials) throws IllegalAccessException {
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

    public boolean registerItem(ItemCredentials itemCredentials) throws IllegalAccessException {
        Item item = new Item(itemCredentials.getName(), itemCredentials.getHit(), itemCredentials.getDefense(), itemCredentials.getHealing(), itemCredentials.getDamage(), itemCredentials.getPrice(), itemCredentials.getDescription(), itemCredentials.getImage());
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

    public boolean registerEnemy(EnemyCredentials enemyCredentials) throws IllegalAccessException {
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

