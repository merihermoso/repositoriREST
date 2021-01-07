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
import edu.upc.dsa.orm.models.Credentials.GetUserCredentials;
import edu.upc.dsa.orm.models.GameCredentials.EnemyCredentials;
import edu.upc.dsa.orm.models.GameCredentials.GameCredentials;
import edu.upc.dsa.orm.models.GameCredentials.ItemCredentials;
import edu.upc.dsa.orm.models.GameCredentials.RankingPositionResponse;
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
@Path("game")
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

    /*********************************************  RANKINGS    *******************************************************/
    //Servicio para obtener las 5 PARTIDAS CON MÁS SCORE
    @GET
    @ApiOperation(value = "Get TOP Games", notes = "Get top GAMES ordered BY score from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer = "List"),
    })
    @Path("/Games/top")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopGames() throws SQLException {

        List<Game> games = this.gameDAO.getGameRanking();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
        };
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "Get TOP Users", notes = "Get top USERS ordered BY score from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer = "List"),
    })
    @Path("/topUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUsers() throws SQLException {

        List<User> users = this.userDAO.getUserRanking();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {
        };
        return Response.status(201).entity(entity).build();

    }

    @POST
    @ApiOperation(value = "Get a user position in ranking", notes = "Get position of a user in the score ranking")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = RankingPositionResponse.class),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/getUserPositionByUsername")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPositionByUsername(GetUserCredentials getUserCredentials) throws SQLException {

        RankingPositionResponse rankingPositionResponse = new RankingPositionResponse(this.userDAO.getUserPositionByUsername(getUserCredentials.getUsername()));

        if (rankingPositionResponse.getPosition() != -1) {
            return Response.status(201).entity(rankingPositionResponse).build();
        } else {
            return Response.status(404).entity(rankingPositionResponse).build();
        }

    }


    /**********************************************     GAMES (partidas) services   ***********************************/
    //Servicio para obtener todas las partidas
    @GET
    @ApiOperation(value = "Get all Games from BBDD", notes = "Get all partidas from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer = "List"),
    })
    @Path("/Games/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames() {

        List<Game> games = this.gameDAO.findAll();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
        };
        return Response.status(201).entity(entity).build();

    }



    //Servicio para obtener la Partida a partir del Username (User)
    @GET
    @ApiOperation(value = "get a Game by Username", notes = "Get all data 1 game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("/Game/getByUSERNAME/{username}")                                             //Partida By Username
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetGameByUsername(@PathParam("username") String username) {
        try {
            Game game = this.gameDAO.getGameByUsername(username);
            return Response.status(200).entity(game).build();
        } catch (Exception e) {

            return Response.status(503).build();
        }
    }

    //Servicio para obtener la Partida a partir del Username (User)
    @GET
    @ApiOperation(value = "get a Game by its ID", notes = "Get all data 1 game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
        @Path("/Game/getByID/{gameID}")    //servicio que obtenia la Partida a partir del ID
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


    // Servicio para registrar una nueva partida
    @POST
    @ApiOperation(value = "Register a new Game", notes = "Register a game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),

    })
    @Path("/Game/register")
    public Response gameRegister(GameCredentials gameCredentials) throws SQLException {

        //   if (orderCredentials.getPrice()==null) return Response.status(602).build();

        this.gameDAO.registerGame(gameCredentials);
        return Response.status(201).build();

    }
/******************************************     ITEMS services  *******************************************************/
    //Servicio para obtener todos los items
    @GET
    @ApiOperation(value = "Get all items from BBDD", notes = "Get all items from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "List"),
    })
    @Path("/Items/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Item> items = this.itemDAO.findAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {
        };
        return Response.status(201).entity(entity).build();

    }
    //Servicio para obtener un Item a partir del ID
    @GET
    @ApiOperation(value = "get an Item by its ID", notes = "Get all data 1 item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well...")

    })

    @Path("/Item/GetByID/{itemID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemById(@PathParam("itemID") int itemID) {
        try {
            Item item = this.itemDAO.getItemById(itemID);
            return Response.status(200).entity(item).build();
        } catch (Exception e) {

            return Response.status(503).build();
        }
    }
    //Servei per obtenir un item a partir del seu nom
    @GET
    @ApiOperation(value = "get an Item by its name", notes = "Get all data one ITEM")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("/Item/getByNAME/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemByName(@PathParam("name") String name) {
        try{
            Item item = this.itemDAO.getItemByName(name);
            return Response.status(200).entity(item).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    //Serrvei per registrar un nou item
    @POST
    @ApiOperation(value = "Register a new Item", notes = "Register a new item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),
    })
    @Path("/Item/register")
    public Response itemRegister(ItemCredentials itemCredentials) throws SQLException {
        this.itemDAO.registerItem(itemCredentials);
        return Response.status(201).build();
    }

    /************************************************   ENEMIES services ***********************************************/
    //Servei per obtenir tots els enemics
    @GET                                                                    //OBTENEMOS TODAS LAS PARTIDAS
    @ApiOperation(value = "Get all Enemy from BBDD", notes = "Get all enemies from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class, responseContainer = "List"),
    })
    @Path("Enemy/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies() {

        List<Enemy> enemy = this.enemyDAO.findAll();

        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemy) {
        };
        return Response.status(201).entity(entity).build();

    }
    //Servei per obtenir un enemic a partir del seu nom
    @GET
    @ApiOperation(value = "get an Enemy by its NAME", notes = "Get all data 1 item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Enemy.class),
            @ApiResponse(code = 503, message = "not working well...")

    })

    @Path("/Enemy/GetByNAME/{name}")
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
    @Path("/Enemy/register")
    public Response enemyRegister(EnemyCredentials enemyCredentials) throws SQLException {

        this.enemyDAO.registerEnemy(enemyCredentials);
        return Response.status(201).build();
    }
    /**********************************************************************************************************/

}



