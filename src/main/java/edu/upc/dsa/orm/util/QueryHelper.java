package edu.upc.dsa.orm.util;

import edu.upc.dsa.orm.models.User;

public class QueryHelper {

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

    public static String createQuerySELECT(Object entity) {          //consulta to GET qualsevol objecte de la bbdd
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    public static String createQuerySELECTAll(Class theClass) {          //consulta to GET qualsevol objecte de la bbdd
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        return sb.toString();
    }

    public static String createQuerySELECTtop(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" ORDER BY score");
        return sb.toString();                      //FALTA FER QUE ORDENI PER SCORE, PERO PETA....
    }

    public static String createQueryUserSELECTbyUsername(String username) {  //consulta to GET qualsevol objecte de la bbdd
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


    //////////////////////////////////////////////////////////////////////////es poden unificar en una sola consulta

    public static String createQueryGameSELECTbyUsername(String username) {          //consulta to GET qualsevol objecte de la bbdd
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Game.* FROM Game, User, UserGame");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserGame.id_player");
        sb.append(" And  Game.id = UserGame.id_game");

        return sb.toString();
    }

    public static String createQueryOrderSELECTbyUsername(String username) {          //consulta to GET qualsevol objecte de la bbdd
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT Orders.* FROM Orders, User, UserOrder");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE User.username = ?");
        sb.append(" And  User.id = UserOrder.id_user");
        sb.append(" And  Orders.id = UserOrder.id_order");

        return sb.toString();
    }                                                                               //DE MOMENT NOMES PRINTEA LA PRIMERA QUE TROBA...

    public static String createQueryElementSELECTbyUsername(String username) {          //consulta to GET qualsevol objecte de la bbdd
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
        sb.append("SELECT ROW_NUMBER FROM User");     //HA DE DIRTE LA TEVA POSICIÓ         ENCARA NO FUNCIONA
        sb.append(" WHERE username = ?");
        sb.append(" ORDER BY score ASC");

        return sb.toString();

    }

                                                                                        //  CONSULTES PER BORRAR    //
    public static String createQueryDELETE(Object object) {  //consulta to GET qualsevol objecte de la bbdd
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE * FROM User");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE username = ?");

        return sb.toString();                      //FALTA FER QUE ORDENI PER SCORE, PERO PETA....
    }


    public static String createQueryEnemySELECTbyName(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM Enemy");      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE name = ?");

        return sb.toString();                      //FALTA FER QUE ORDENI PER SCORE, PERO PETA....
    }
}
