package edu.upc.dsa;

import edu.upc.dsa.orm.SessionImpl;
import edu.upc.dsa.orm.models.User;
import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBJDBC {                                       //PROVAR OPERACIONES 'SIN CLIENTE'
    private static java.lang.Object Object;

    private static final boolean remote_machine = false;

    private static final String remote_ip = "147.83.7.207";
    private static final int remote_port = 8080;

    private static final String local_ip = "localhost";
    private static final int local_port = 8080;

    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in edu.upc.dsa package
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.orm.services");

        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();

        if (remote_machine) {
            beanConfig.setHost(remote_ip + ":" + local_port);
        } else {
            beanConfig.setHost(local_ip + ":" + remote_port);
        }

        beanConfig.setBasePath("/dsaApp");
        beanConfig.setContact("support@grup5dsa.com");
        beanConfig.setDescription("REST API for GAME G5 Manager");
        beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        beanConfig.setResourcePackage("edu.upc.dsa.orm.services");
        beanConfig.setTermsOfServiceUrl("http://www.example.com/resources/eula");
        beanConfig.setTitle("REST API");
        beanConfig.setVersion("1.0.0");
        beanConfig.setScan(true);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application
        if (remote_machine) {
            return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://" + remote_ip + ":" + remote_port + "/dsaApp/"), rc);
        } else {
            return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://" + local_ip + ":" + local_port + "/dsaApp/"), rc);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////USERS
    public static void insert() throws SQLException{
        Connection connection = null;
        try {
            connection = DBUtils.getConnection();         //demano recursos

            SessionImpl s = new SessionImpl(connection);

            s.save(new User("Piliaaa", "piliaaa@email.com" ,"Piliaaa16",23));


            /*Statement statement1 = connection.createStatement();            //creo una sentencia i la executo a la linea de sota
            statement1.execute("INSERT INTO User (userID, username, email, password, nivel) " +
                    "VALUES (16,'Pili','pili@email.com','Pili16',2)");

             */
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
            ResultSet rs=stmt.executeQuery("SELECT * FROM User");

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
            ResultSet rs=stmt.executeQuery("SELECT * FROM User");

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

        insert();                  //works ok
        findAllUsers();             //works ok
        //getUser(6);              //works ok

        //insertpartida();            //works oks
        //getPartida(5);          //works ok
        //findAllPartidas();

        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        if (remote_machine) {
            System.out.println("Jersey app started with WADL available at "
                    + "http://" + remote_ip + ":" + remote_port + "/dsaApp/application.wadl\nHit enter to stop it...");
        } else {
            System.out.println("Jersey app started with WADL available at "
                    + "http://" + local_ip + ":" + local_port + "/dsaApp/application.wadl\nHit enter to stop it...");
        }

        System.in.read();
        server.stop();
    }
}
