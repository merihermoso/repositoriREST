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
import edu.upc.dsa.orm.models.API.ProfileResponse;
import edu.upc.dsa.orm.models.API.RankingPositionResponse;
import edu.upc.dsa.orm.models.GameCredentials.*;
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

    private final GameDAO gameDAO;
    private final UserDAO userDAO;
    private final ItemDAO itemDAO;
    private final EnemyDAO enemyDAO;
    private final PlayerDAO playerDAO;

    public GameService() {
        this.gameDAO = GameDAOImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();
        this.playerDAO = PlayerDAOImpl.getInstance();
        this.itemDAO = ItemDAOImpl.getInstance();
        this.enemyDAO = EnemyDAOImpl.getInstance();
    }

    /*********************************************  resum    *******************************************************/
    /***
     * /Games/topGames
     * /getTopUsers"
     * /getUserPositionByUsername/{username}
     *
     * /Games/findAll
     * /Game/getByUSERNAME/{username}
     * /Game/getByID/{gameID}
     * /Game/register
     *
     * /Players/findAll
     * /Player/getByUSERNAME/{username}
     * /Player/getByID/{playerID}
     * /Player/register
     *
     * /Items/findAll
     * /Item/GetByID/{itemID}
     * /Item/getByNAME/{name}
     * /Item/register
     *
     * /Enemy/findAll
     * /Enemy/GetByNAME/{name}
     * /Enemy/register
     /*********************************************  RANKINGS    *******************************************************/

    //Servicio para obtener las 5 PARTIDAS CON MÁS SCORE
    @GET
    @ApiOperation(value = "Get TOP Games")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer = "List"),
    })
    @Path("/getTopGames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopGames() throws SQLException {
        List<Game> games = this.gameDAO.getGameRanking();
        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
        };
        return Response.status(201).entity(entity).build();
    }


    @GET
    @ApiOperation(value = "Get the users with the most score")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ProfileResponse.class, responseContainer = "List"),
    })
    @Path("/getTopUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUsers() throws SQLException {
        List<User> users = this.gameDAO.getUserRanking();
        List<ProfileResponse> profileResponses = new ArrayList<>();
        for(User user : users) {
            profileResponses.add(new ProfileResponse(user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getScore(),
                    user.getLevel(),
                    this.gameDAO.getUserPositionByUsername(user.getUsername())));
        }
        GenericEntity<List<ProfileResponse>> entity = new GenericEntity<List<ProfileResponse>>(profileResponses) {
        };
        return Response.status(201).entity(entity).build();
    }

    //Servicio para obtener la posición en el ranking del usuario
    @GET
    @ApiOperation(value = "Get a user position in ranking")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = RankingPositionResponse.class),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 404, message = "User not exists"),
    })
    @Path("/getUserPositionByUsername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserPositionByUsername(@PathParam("username") String username) throws SQLException {
        if (username == null) return Response.status(601).build();
        if (this.userDAO.userExists(username)) {
            RankingPositionResponse rankingPositionResponse =
                    new RankingPositionResponse(this.gameDAO.getUserPositionByUsername(username));
            return Response.status(201).entity(rankingPositionResponse).build();
        } else {
            return Response.status(404).build();
        }
    }

    /**********************************************     GAMES (partidas) services   ***********************************/
    //Servicio para obtener todas las partidas
    @GET
    @ApiOperation(value = "Get all Games from BBDD")
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
    @ApiOperation(value = "get a Game by Username")
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
    @ApiOperation(value = "get a Game by its ID")
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
    @ApiOperation(value = "Register a new Game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),
    })
    @Path("/Game/register")
    public Response gameRegister(GameCredentials gameCredentials) throws SQLException {

        //   if (orderCredentials.getPrice()==null) return Response.status(602).build();

        this.gameDAO.registerGame(gameCredentials);
        return Response.status(201).build();

    }

    /**************************************     PLAYER services ******************************************************/

    //Servicio para obtener todas los players
    @GET
    @ApiOperation(value = "Get all Players from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class, responseContainer = "List"),
    })
    @Path("/Players/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers() {
        List<Player> players = this.playerDAO.findAll();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(players) {
        };
        return Response.status(201).entity(entity).build();
    }

    //Servicio para obtener el player a partir del Username (User)
    @GET
    @ApiOperation(value = "get the Player by Username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/Player/getByUSERNAME/{username}")                                             //Partida By Username
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetPlayerByUsername(@PathParam("username") String username) {
        try {
            Player player = this.playerDAO.getPlayerByUsername(username);
            return Response.status(200).entity(player).build();
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
    @Path("/Player/getByID/{playerID}")    //servicio que obtenia la Partida a partir del ID
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetPlayerById(@PathParam("playerID") int playerID) {
        try{
            Player player = this.playerDAO.getPlayerById(playerID);
            return Response.status(200).entity(player).build();
        }
        catch (Exception e){
            return Response.status(503).build();
        }
    }


    // Servicio para registrar una nueva partida
    @POST
    @ApiOperation(value = "Register a new Player")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),
    })
    @Path("/Player/register")
    public Response playerRegister(PlayerCredentials playerCredentials) throws SQLException {

        //   if (orderCredentials.getPrice()==null) return Response.status(602).build();

        this.playerDAO.registerPlayer(playerCredentials);
        return Response.status(201).build();
    }

    /***************************************    modificacions Player    ***********************************************/
    /*
    *
    *
     */





/******************************************     ITEMS services  *******************************************************/
    //Servicio para obtener todos los items
    @GET
    @ApiOperation(value = "Get all items from BBDD")
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
    @ApiOperation(value = "get an Item by its ID")
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
    @ApiOperation(value = "get an Item by its name")
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
    // Servei per obtenir un element a partir del username del usuari
    @GET                                                                            //Servicio para obtener el elemento de un Usuario (USERNAME)
    @ApiOperation(value = "get an Item by Username", notes = "Get all data 1 element")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well..."),
            @ApiResponse(code = 600, message = "Need to fill in username field.")

    })
    @Path("/Element/getByUSERNAME/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemFromUsername(@PathParam("username") String username) {
        try{
            if (username==null) return Response.status(600).build();
            Item item = this.itemDAO.getItemByUsername(username);
            return Response.status(200).entity(item).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    @POST
    @ApiOperation(value = "Get an item ID", notes = "Get itemID by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjectIdResponse.class),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/Item/getIdByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdByName(GetItemCredentials getItemCredentials) throws SQLException {

        ObjectIdResponse objectIdResponse = new ObjectIdResponse(this.itemDAO.getIdByName(getItemCredentials.getName()));

        if (objectIdResponse.getObjectID() != -1) {
            return Response.status(201).entity(objectIdResponse).build();
        } else {
            return Response.status(404).entity(objectIdResponse).build();
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



