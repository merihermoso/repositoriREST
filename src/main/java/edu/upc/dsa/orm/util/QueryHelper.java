package edu.upc.dsa.orm.util;

import edu.upc.dsa.orm.models.User;

public class QueryHelper {

    /***************************************    INSERT      ***********************************************************/
                                                                    //consulta general (serveix per insertar on sigui)
    public static String createQueryINSERT(Object entity) {         //consultes que han de insertar qualsevol objecte a la bbdd
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        int i = 0;
        for (String field: fields) {
            if (i == 0) {
                sb.append(field);
            } else {
                sb.append(", ").append(field);
            }
            i++;
        }

        sb.append(") VALUES (");

        i = 0;
        for (String field: fields) {
            if (i == 0) {
                sb.append("?");
            } else {
                sb.append(", ?");
            }
            i++;
        }

        sb.append(")");

        return sb.toString();
    }
    /***************************************    SELECTs      ***********************************************************/

    //serveix per tots
    public static String createQuerySELECTbyID(Object entity) {          //consulta to GET by ID
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    public static String createQuerySELECTbyName(Object entity) {          //consulta to GET by ID
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE name = ?");

        return sb.toString();
    }

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQuerySELECTAll(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        return sb.toString();
    }

    public static String createQuerySELECTtop20(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" ORDER BY score DESC LIMIT 20");
        return sb.toString();                      //FALTA FER QUE ORDENI PER SCORE, PERO PETA....
    }

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryUserSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM User");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE username = ?");

        return sb.toString();                      //FALTA FER QUE ORDENI PER SCORE, PERO PETA....
    }

    //SELECT password FROM User WHERE username = "?"
    public static String createQueryUserSELECTPasswordByUsername() {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT password FROM User");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE username = ?");

        return sb.toString();

    }

    /**************************     SELECT IDs     ************************************/
    //SELECT itemID FROM Item WHERE name = "?"
    public static String createQueryItemIdSELECTbyName()  {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id FROM Item WHERE name = ? ");
        return sb.toString();
    }

    //SELECT idClass FROM Class WHERE name=?                        //generica per obtenir el id a partir del nom (not working yet)
    public static String createQueryGetIDbyName(Object entity){
        int res=0;
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id"+entity.getClass().getSimpleName()+" FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE name = ?");
        return sb.toString();
    }

    //SELECT userID FROM User WHERE username = "?"
    public static String createQueryIdSELECTbyUsername()  {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id FROM User WHERE username = ? ");
        return sb.toString();

    }
//Select price from item
    public static String createQuerySELECTPriceItem(int id_item){
        StringBuffer sb = new StringBuffer("SELECT price FROM Item WHERE id_item='");
        sb.append(id_item);
        sb.append("'");
        return sb.toString();
    }

    //Select coins from Player
    public static String createQuerySELECTCoinsPlayer(int id_player){
        StringBuffer sb = new StringBuffer("SELECT coins FROM Player WHERE id_player='");
        sb.append(id_player);
        sb.append("'");
        return sb.toString();
    }


/**************************CONSULTES AMB RELACIONS ENTRE DIFERENTS TAULES ************************************/

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryGameSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Game.* FROM Game, User, Player, PlayerGame");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        sb.append(" And  Player.id = PlayerGame.id_player");
        sb.append(" And  Game.id = PlayerGame.id_game");
        return sb.toString();
    }
    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryPlayerSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Player.id, Player.status, Player.coins, Player.score, Player.numLevel, Player.speed, Player.hit, Player.defense, Player.healing, Player.damage FROM Player, User, UserPlayer");
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        return sb.toString();
    }
                                                                             //DE MOMENT NOMES PRINTEA LA PRIMERA QUE TROBA...
    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryItemSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * Item FROM Item,User, Inventory");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = Inventory.id_user");
        sb.append(" And  Item.id = Inventory.id_item");
        return sb.toString();
    }

    //SELECT position and score FROM User WHERE username = "?"
    public static String createQueryUserPositionSELECTbyUsername()  {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT 1 + (SELECT count( * ) FROM User a WHERE a.score > b.score ) AS rank FROM User b WHERE username = ? ORDER BY rank LIMIT 1");

        return sb.toString();

    }
    /**************************CONSULTES AMB RELACIONS ENTRE DIFERENTS TAULES ************************************/
                                                                                        //  CONSULTES PER ELIMINAR     //
    //DELETE FROM Class WHERE ID = ?
    public static String createQueryUPDATEtoInactive(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE "+entity.getClass().getSimpleName()+" SET status='inactive'");
        sb.append(" WHERE id"+entity.getClass().getSimpleName()+" = ?");
        return sb.toString();
    }

    public static String createQueryDELETE(Object entity){
        // FALTA CORREGIR

        StringBuffer sb = new StringBuffer("DELETE FROM");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("WHERE");
        sb.append("?").append(" = ");
        sb.append("?").append(" ");

        return sb.toString();
    }

    /*************************      UPDATE      *****************************************************************/

    public static String createQueryUPDATE(Object entity){

        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET ");
        String field;
        int i =1;
        while (i<fields.length){
            field = fields[i];
            if (i>1) sb.append(" = ?, ");
            sb.append(field);
            i++;
        }
        sb.append(" = ?");
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEinventoryByUser(Object entity){

        String [] fields = ObjectHelper.getFields(entity);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("SET ");
        String field;
        int i =1;
        while (i<fields.length){
            field = fields[i];
            if (i>1) sb.append(" = ?, ");
            sb.append(field);
            i++;
        }
        sb.append(" = ?");
        sb.append(" WHERE id_user = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEPasswordByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE User SET password = ?");
        sb.append(" WHERE username = ? ");
        return sb.toString();
    }

    public static String createQueryUPDATEEmailByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE User SET email = ?");
        sb.append(" WHERE username = ? ");
        return sb.toString();
    }

    public static String createQueryUPDATEStatusByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE User SET status = ?");
        sb.append(" WHERE username = ? ");
        return sb.toString();
    }
    public static String createQueryUPDATELevelByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE User SET level = ?");
        sb.append(" WHERE username = ? ");
        return sb.toString();
    }
    public static String createQueryUPDATEScoreByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE User SET score = ?");
        sb.append(" WHERE username = ? ");
        return sb.toString();
    }

    /********************************** modificacions player **********************************************************/
    public static String createQueryPlayerUPDATELevelByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player SET Player.level = ?");
        sb.append(" FROM User,Player,UserPlayer ");
        sb.append(" WHERE User.username = ? ");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        return sb.toString();
    }
    public static String createQueryPlayerUPDATEScoreByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player SET Player.score = ?");
        sb.append(" FROM User,Player,UserPlayer ");
        sb.append(" WHERE User.username = ? ");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        return sb.toString();
    }
    public static String createQueryPlayerUPDATECoinsByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player SET Player.coins = ?");
        sb.append(" FROM User,Player,UserPlayer ");
        sb.append(" WHERE User.username = ? ");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        return sb.toString();
    }
    public static String createQueryPlayerUPDATESpeedByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player SET Player.speed = ?");
        sb.append(" FROM User,Player,UserPlayer ");
        sb.append(" WHERE User.username = ? ");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        return sb.toString();
    }
    public static String createQueryPlayerUPDATEStatusByUsername() {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player SET Player.status = ?");
        sb.append(" FROM User,Player,UserPlayer ");
        sb.append(" WHERE User.username = ? ");
        sb.append(" And  User.id = UserPlayer.id_user");
        sb.append(" And  Player.id = UserPlayer.id_player");
        return sb.toString();
    }



}
