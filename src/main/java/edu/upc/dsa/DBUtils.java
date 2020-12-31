package edu.upc.dsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {                                            //NO TOCAR!!!
    private static final String DB_NAME = "dsabbdd";              //database name
    private static final String DB_HOST = "127.0.0.1" ;           //demomento este puerto en local, luego ip màq.
    private static final String DB_USER = "root" ;                //usuario bbdd
    private static final String DB_PASS = "root";                 //contra que tengo en mariaDB
    private static final String DB_PORT = "3306" ;                //puerto por defecto mysql

    public static String getDb() { return DB_NAME; }
    public static String getDbHost() {return DB_HOST; }
    public static String getDbUser() {return DB_USER; }
    public static String getDbPasswd() {return DB_PASS; }
    public static String getDbPort() {return DB_PORT; }

    public static Connection getConnection() throws SQLException {
        String db = DBUtils.getDb();
        String host = DBUtils.getDbHost();
        String user = DBUtils.getDbUser();
        String pass = DBUtils.getDbPasswd();
        String port = DBUtils.getDbPort();

        Connection connection = DriverManager.getConnection("jdbc:mariadb://"+host+":"+port+"/"+
                db+"?user="+user+"&password="+pass);

        return connection;
    }
}
