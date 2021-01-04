package edu.upc.dsa.orm;


import edu.upc.dsa.orm.util.DBUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class FactorySession {

    public static Session openSession() {

        try {
            Connection conn = getConnection();
            Session session = new SessionImpl(conn);
            return session;

        } catch (SQLException sqlException) {
            return null;
        }

    }

    public static Connection getConnection() throws SQLException {

        return DBUtils.getConnection();

    }
}
