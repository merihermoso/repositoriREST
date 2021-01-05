package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.enemy.EnemyDAO;
import edu.upc.dsa.orm.dao.enemy.EnemyDAOImpl;
import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;

import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.*;

@Api(value = "/game")
@Path("/game")
public class GameService {

    private GameDAO gameDAO;
    private UserDAO userDAO;
    private ItemDAO itemDAO;
    private EnemyDAO enemyDAO;

    public GameService() {
        this.gameDAO = GameDAOImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();
        this.itemDAO = ItemDAOImpl.getInstance();
        this.enemyDAO = EnemyDAOImpl.getInstance();
    }

    @GET                                                                    //Servicio para obtener todas las partidas
    @ApiOperation(value = "Get all Partidas", notes = "Get all partidas from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer = "List"),
    })
    @Path("AllGames/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames() {

        List<Game> games = this.gameDAO.findAll();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
        };
        return Response.status(201).entity(entity).build();

    }


    @GET                                                                    //Servicio para obtener las 5 PARTIDAS CON MÁS SCORE
    @ApiOperation(value = "Get TOP Games", notes = "Get top GAMES ordered BY score from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer = "List"),
    })
    @Path("topGames/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopGames() throws SQLException {

        List<Game> games = this.gameDAO.getGameRanking();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
        };
        return Response.status(201).entity(entity).build();

    }

    @GET                                                                    //Servicio para obtener las 5 PARTIDAS CON MÁS SCORE
    @ApiOperation(value = "Get TOP Users", notes = "Get top USERS ordered BY score from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "List"),
    })
    @Path("topUsers/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUsers() throws SQLException {

        List<User> users = this.userDAO.getUserRanking();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {
        };
        return Response.status(201).entity(entity).build();

    }


    @GET                                                                    //Servicio para obtener la Partida a partir del Username (User)
    @ApiOperation(value = "get a Game", notes = "Get all data 1 game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("getGame/{username}")                                             //Partida By Username
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetGameByUsername(@PathParam("username") String username) {
        try {
            Game game = this.gameDAO.getGameByUsername(username);
            return Response.status(200).entity(game).build();
        } catch (Exception e) {

            return Response.status(503).build();
        }
    }

    /*
        @Path("getGame/{gameID}")                                               //servicio que obtenia la Partida a partir del ID
        @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
        public Response GetGameById(@PathParam("gameID") int gameID) {
            try{
                Game game = this.gameDAO.getGameById(gameID);
                return Response.status(200).entity(game).build();
            }
            catch (Exception e){

                return Response.status(503).build();
            }
        }
    */

                                                                                    // Servicio para registrar una nueva partida
    @POST
    @ApiOperation(value = "Register a new Game", notes = "Register a game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),


    })
    @Path("/registerGame")
    public Response gameRegister(GameCredentials gameCredentials) throws SQLException {

        //   if (orderCredentials.getPrice()==null) return Response.status(602).build();

        this.gameDAO.registerGame(gameCredentials);
        return Response.status(201).build();

    }

                                                                                                        ////// ITEMS SERVICE /////

                                                                            //Servicio para obtener todos los items
    @GET
    @ApiOperation(value = "Get all items", notes = "Get all items from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "List"),
    })
    @Path("AllItems/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Item> items = this.itemDAO.findAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {
        };
        return Response.status(201).entity(entity).build();

    }

    @GET                                                                         //Servicio para obtener un Item a partir del ID
    @ApiOperation(value = "get an Item", notes = "Get all data 1 item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well...")

    })

    @Path("/GetItem/{itemID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemById(@PathParam("itemID") int itemID) {
        try {
            Item item = this.itemDAO.getItemById(itemID);
            return Response.status(200).entity(item).build();
        } catch (Exception e) {

            return Response.status(503).build();
        }
    }
                                                                                //Servicio para registrar un nuevo Item
    @POST
    @ApiOperation(value = "Register a new Item", notes = "Register a new item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),
    })
    @Path("/registerItem")
    public Response itemRegister(ItemCredentials itemCredentials) throws SQLException {
        this.itemDAO.registerItem(itemCredentials);
        return Response.status(201).build();
    }

    ///////////////////////////////////////////////////////////////////////         ENEMY           ////////////////////////

    @GET                                                                    //OBTENEMOS TODAS LAS PARTIDAS
    @ApiOperation(value = "Get all Enemy", notes = "Get all enemies from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class, responseContainer = "List"),
    })
    @Path("AllEnemies/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies() {

        List<Enemy> enemy = this.enemyDAO.findAll();

        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemy) {
        };
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get an Enemy", notes = "Get all data 1 item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Enemy.class),
            @ApiResponse(code = 503, message = "not working well...")

    })

    @Path("/GetEnemy/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetEnemyByName(@PathParam("name") String name) {
        try {
            Enemy enemy = this.enemyDAO.getEnemyByName(name);
            return Response.status(200).entity(enemy).build();
        } catch (Exception e) {

            return Response.status(503).build();
        }
    }
                                                                        //Servicio para registar un nuevo enemigo
    @POST
    @ApiOperation(value = "Register a new Enemy", notes = "Register a new item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),

    })
    @Path("/registerEnemy")
    public Response enemyRegister(EnemyCredentials enemyCredentials) throws SQLException {

        this.enemyDAO.registerEnemy(enemyCredentials);
        return Response.status(201).build();
    }
}



