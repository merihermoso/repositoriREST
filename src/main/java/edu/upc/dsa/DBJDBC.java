package edu.upc.dsa;

import edu.upc.dsa.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBJDBC {                                       //PROVAR OPERACIONES 'SIN CLIENTE'
    private static java.lang.Object Object;
////////////////////////////////////////////////////////////////////////////////////////////////////USERS
    public static void insert() throws SQLException{
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
    private static String getType (int type){
        return null;
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
    public static void findAllUsers() throws SQLException{
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

    ///////////////////////////////////////////////////////////////////////////////////////////PARTIDAS
    public static void insertpartida() throws SQLException{
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            Statement statement3 = connection.createStatement();            //creo una sentencia i la executo a la linea de sota
            statement3.execute("INSERT INTO Partidas(partidaID, fechaInicio, horaInicio, fechaFin, horaFin, score_partida) VALUES (11,'20/12/20','18:34','20/12/20','22:42',1200)");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            connection.close();                         //asseguro que tanco conexió
        }
    }

    public static void getPartida(int partidaID) throws SQLException{
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery("SELECT * FROM Partidas");

            //getting the record of 3rd row
            rs.absolute(partidaID);
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)+ " " + rs.getString(5));

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            connection.close();                         //asseguro que tanco conexió
        }
    }
    public static void findAllPartidas() throws SQLException{
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            Statement stmt=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=stmt.executeQuery("SELECT * FROM Partidas");

            //getting the record of 3rd row
            //rs.absolute(3);
            while(rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)+" " + rs.getString(5));
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

        //insertpartida();            //works oks
        //getPartida(5);          //works ok
        //findAllPartidas();

    }
}
