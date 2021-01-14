package edu.upc.dsa.orm.util;

import edu.upc.dsa.orm.models.User;

public class QueryHelper {

    // CREATE

    public static String createQueryINSERT(Object object) {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(object.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(object);

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

        System.out.println(sb);

        return sb.toString();
    }

    // READ

    public static String createQuerySELECTbyParameter(Class theClass, String byParameter) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE " + byParameter + " = ?");

        System.out.println(sb);

        return sb.toString();
    }

    public static String createQuerySELECTparameterByParameter(Class theClass, String parameter, String byParameter) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT " + parameter + " FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + byParameter + " = ?");

        System.out.println(sb);

        return sb.toString();
    }

    //consulta to GET qualsevol objecte de la bbdd
    public static String createQuerySELECTall(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        System.out.println(sb);

        return sb.toString();
    }

    public static String createQuerySELECTallExtras(Class theClass, String extras) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" " + extras);

        System.out.println(sb);

        return sb.toString();
    }

    //SELECT position and score FROM User WHERE username = "?"
    public static String createQueryUserPositionSELECTbyParameter(String byParameter)  {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT 1 + (SELECT count( * ) FROM User a WHERE a.score > b.score ) AS rank FROM User b WHERE " + byParameter + " = ? ORDER BY rank LIMIT 1");

        System.out.println(sb);

        return sb.toString();

    }

    // UPDATE

    public static String createQueryUPDATE(Object object){

        String [] fields = ObjectHelper.getFields(object);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(object.getClass().getSimpleName()).append(" ");
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
        sb.append(" WHERE id = ?");

        System.out.println(sb);

        return sb.toString();
    }

    public static String createQueryUPDATEbyParameter(Object object, String parameter){

        String [] fields = ObjectHelper.getFields(object);
        StringBuffer sb = new StringBuffer("UPDATE ");
        sb.append(object.getClass().getSimpleName()).append(" ");
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
        sb.append(" WHERE " + parameter + " = ?");

        System.out.println(sb);

        return sb.toString();
    }

    public static String createQueryUPDATEparameterByParameter(Class theClass, String parameter, String byParameter) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE " + theClass.getSimpleName() + " SET " + parameter + " = ? ");
        sb.append(" WHERE " + byParameter + " = ? ");

        System.out.println(sb);

        return sb.toString();
    }


    // DELETE

    public static String createQueryDELETE(Object entity){

        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(entity.getClass().getSimpleName());
        sb.append(" WHERE id = ?");

        System.out.println(sb);

        return sb.toString();

    }

    public static String createQueryDELETEbyParameter(Class theClass, String parameter){

        StringBuffer sb = new StringBuffer("DELETE FROM ");
        sb.append(theClass.getSimpleName());
        sb.append(" WHERE " + parameter + " = ?");

        System.out.println(sb);

        return sb.toString();
    }

}
