package edu.upc.dsa.orm.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {         //consultes que han de insertar cualsevols objecte a la bbdd
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("ID");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECT(Object entity) {          //consulta to GET cualsevol objecte de la bbdd
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());      //totes les files de la taula que tinguin el id=
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }
}
