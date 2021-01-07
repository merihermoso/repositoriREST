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
    //SELECT idClass FROM Class WHERE name=?
    public static String createQueryGetIDbyName(Object entity){
        int res=0;
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id"+entity.getClass().getSimpleName()+" FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE name = ?");
        return sb.toString();
    }

    //SELECT idClass FROM Class WHERE name=?
    public static String createQueryGetIDbyUsername(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT id"+entity.getClass().getSimpleName()+" FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE username = ?");
        return sb.toString();
    }


/**************************CONSULTES AMB RELACIONS ENTRE DIFERENTS TAULES ************************************/

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryGameSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Game.* FROM Game, User, UserGame");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserGame.id_player");
        sb.append(" And  Game.id = UserGame.id_game");

        return sb.toString();
    }

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryOrderSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Orders.* FROM Orders, User, UserOrder");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserOrder.id_user");
        sb.append(" And  Orders.id = UserOrder.id_order");

        return sb.toString();
    }                                                                               //DE MOMENT NOMES PRINTEA LA PRIMERA QUE TROBA...

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQueryElementSELECTbyUsername(String username) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Element.id, Element.name FROM Element, OrderElement,Orders, User, UserOrder");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserOrder.id_user");
        sb.append(" And  Orders.id = UserOrder.id_order");
        sb.append(" And  Element.id = OrderElement.id_element");

        return sb.toString();
    }
    //SELECT position and score FROM User WHERE username = "?"
    public static String createQueryUserPositionSELECTbyUsername(String username)  {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT COUNT ROW_NUMBER FROM User");     //HA DE DIRTE LA TEVA POSICIÓ         ENCARA NO FUNCIONA
        sb.append(" WHERE username = ?");
        sb.append(" ORDER BY score ASC");

        return sb.toString();

    }
    /**************************CONSULTES AMB RELACIONS ENTRE DIFERENTS TAULES ************************************/
                                                                                        //  CONSULTES PER ELIMINAR     //
    //DELETE FROM Class WHERE ID = ?
    public static String createQueryDELETE(Object entity){
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE "+entity.getClass().getSimpleName()+" SET status='inactive'");
        sb.append(" WHERE id"+entity.getClass().getSimpleName()+" = ?");
        return sb.toString();
    }

    /*************************      UPDATE      *****************************************************************/

    //UPDATE User SET id = ?, nombre = ?, mail = ? WHERE ID = 'user.getId()'
    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(entity.getClass().getSimpleName());
        sb.append(" SET ");

        String[] fields = ObjectHelper.getFields(entity);
       /* if(entity.getClass()== Inventario.class){
            sb.append("cantidad=? WHERE idObjeto=? AND idJugador=?");
        }
        else {*/
            sb.append("id" + entity.getClass().getSimpleName() + " = ?");

            for (String field : fields) {
                if (!field.startsWith("id")) sb.append(", ").append(field).append(" = ?");
            }
            sb.append(" WHERE id" + entity.getClass().getSimpleName() + " = '" +
                    ObjectHelper.getter(entity, "id" + entity.getClass().getSimpleName()) + "'");
     //   }
        return sb.toString();
    }





}
