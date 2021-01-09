package edu.upc.dsa.orm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    private static final String DB_NAME = "dsaBBDD";              //database name
    private static final String DB_HOST = "127.0.0.1";           //de momento este puerto en local, luego ip màq.
    private static final String DB_USER = "root";                //usuario bbdd
    private static final String DB_PASS = "Mazinger72";                 //contra que tengo en mariaDB
    private static final String DB_PORT = "3306" ;                //puerto por defecto mysql

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mariadb://" + DB_HOST + ":" + DB_PORT + "/" +
                DB_NAME + "?user=" + DB_USER + "&password=" + DB_PASS);

        return connection;

    }
}
