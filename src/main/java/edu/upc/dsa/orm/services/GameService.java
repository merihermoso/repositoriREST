package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.enemy.EnemyDAO;
import edu.upc.dsa.orm.dao.enemy.EnemyDAOImpl;
import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;

import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.dao.player.PlayerDAO;
import edu.upc.dsa.orm.dao.player.PlayerDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/game")
@Path("game")
public class GameService {

    private final GameDAO gameDAO;
    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private final EnemyDAO enemyDAO;
    private final PlayerDAO playerDAO;

    public GameService() {
        gameDAO = GameDAOImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();
        this.playerDAO = PlayerDAOImpl.getInstance();
        this.itemDAO = ItemDAOImpl.getInstance();
        this.enemyDAO = EnemyDAOImpl.getInstance();
    }


    @GET
    @ApiOperation(value = "Get all Games from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer = "List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames() {
        List<Game> games = gameDAO.readAll();
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
        };
        return Response.status(200).entity(entity).build();
    }

    //Servicio para obtener la Partida a partir del Username (User)
    @GET
    @ApiOperation(value = "get a Game by Username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("/{username}")                                             //Partida By Username
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetGameByUsername(@PathParam("username") String username) {
        try {
            Game game = gameDAO.readByParameter("username", username);
            return Response.status(200).entity(game).build();
        } catch (Exception e) {
            return Response.status(503).build();
        }
    }

    //Servicio para obtener la Partida a partir del Username (User)
    @GET
    @ApiOperation(value = "get a Game by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/id/{id}")    //servicio que obtenia la Partida a partir del ID
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetGameById(@PathParam("id") int id) {
        try{
            Game game = gameDAO.readByParameter("id", id);
            return Response.status(200).entity(game).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    // Servicio para registrar una nueva partida
    @POST
    @ApiOperation(value = "Register a new Game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful! Game registered"),
    })
    @Path("/")
    public Response gameRegister(Game game) {

        if (gameDAO.create(game)) {
            return Response.status(200).build();
        } else {
            return Response.status(200).build();
        }

    }

    //Per modificar una partida sencera
    @PUT
    @ApiOperation(value = "Update game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateGame(Game game) {

        if (gameDAO.update(game)) {
            return Response.status(200).entity(game).build();
        } else {
            return Response.status(404).build();
        }
    }

    /**************************************     PLAYER services ******************************************************/

    //Servicio para obtener todas los players
    @GET
    @ApiOperation(value = "Get all Players from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class, responseContainer = "List"),
    })
    @Path("/player")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        List<Player> players = this.playerDAO.readAll();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(players) {
        };
        return Response.status(200).entity(entity).build();
    }


    @PUT
    @ApiOperation(value = "Update player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/player")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdatePlayer(Player player) {

        if (playerDAO.update(player)) {

            return Response.status(200).entity(player).build();

        } else {

            return Response.status(400).build();

        }

    }

    /************************************************   ENEMIES services ***********************************************/
    //Servei per obtenir tots els enemics
    @GET                                                                    //OBTENEMOS TODAS LAS PARTIDAS
    @ApiOperation(value = "Get all Enemy from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Enemy.class, responseContainer = "List"),
    })
    @Path("/enemy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies() {

        List<Enemy> enemy = enemyDAO.readAll();

        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemy) {
        };
        return Response.status(200).entity(entity).build();

    }


    //Servicio para obtener un Enemy a partir del ID
    @GET
    @ApiOperation(value = "get an Enemy by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Enemy.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/enemy/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetEnemyById(@PathParam("id") int id) {
        try {
            Enemy enemy = enemyDAO.readByParameter("id", id);
            return Response.status(200).entity(enemy).build();
        } catch (Exception e) {

            return Response.status(503).build();
        }
    }


    //Servei per obtenir un enemic a partir del seu nom
    @GET
    @ApiOperation(value = "get an Enemy by its NAME")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Enemy.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/enemy/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetEnemyByName(@PathParam("name") String name) {
        try {
            Enemy enemy = enemyDAO.readByParameter("name", name);
            return Response.status(200).entity(enemy).build();
        } catch (Exception e) {
            return Response.status(503).build();
        }
    }


    @PUT
    @ApiOperation(value = "Update an Enemy")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "Exception sql..."),
            @ApiResponse(code = 400, message = "not found")
    })
    //Servei per modificar tot l'enemic
    @Path("/enemy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateEnemy(Enemy enemy) {

        if (enemyDAO.update(enemy)) {
            return Response.status(200).entity(enemy).build();
        }
        else{
            return Response.status(400).build();
        }

    }


}



