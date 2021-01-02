package edu.upc.dsa.orm.dao.user;

import edu.upc.dsa.DBUtils;
import edu.upc.dsa.orm.FactorySession;
import edu.upc.dsa.orm.Session;
import edu.upc.dsa.orm.models.User;
import org.apache.log4j.Logger;

import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


public class UserBBDD {
    private static java.lang.Object Object;

    public static void insert() throws SQLException{                //INSERT USER
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            Statement statement1 = connection.createStatement();            //creo una sentencia i la executo a la linea de sota
            statement1.execute("INSERT INTO Users (userID, username, email, password, nivel) " +
                    "VALUES (16,'Pili','pili@email.com','Pili16',2)");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            connection.close();                         //asseguro que tanco conexió
        }
    }


    public static void getUser(int id) throws SQLException{
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery("SELECT * FROM Users");

            //getting the record of 3rd row
            rs.absolute(id);
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            connection.close();                         //asseguro que tanco conexió
        }
    }


    public static void findAllUsers() throws SQLException{                  //OBTENIM TOTS ELS USUARIS
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery("SELECT * FROM Users");

            //getting the record of 3rd row
            //rs.absolute(3);
            while(rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            connection.close();                         //asseguro que tanco conexió
        }
    }
    public static void main(String[] args) throws Exception {
        //insert();                  //works ok
        findAllUsers();             //works ok
        //getUser(6);              //works ok
    }
}