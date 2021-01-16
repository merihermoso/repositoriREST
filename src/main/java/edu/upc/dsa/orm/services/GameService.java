package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.enemy.EnemyDAO;
import edu.upc.dsa.orm.dao.enemy.EnemyDAOImpl;
import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;
import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.models.Enemy;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/game")
@Path("game")
public class GameService {

    private final GameDAO gameDAO;
    private final EnemyDAO enemyDAO;

    public GameService() {

        gameDAO = GameDAOImpl.getInstance();
        enemyDAO = EnemyDAOImpl.getInstance();

    }

    // CREATE

    @POST
    @ApiOperation(value = "Create a game")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 404, message = "User not exists")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGameById(Game game) {

        gameDAO.create(game);
        return Response.status(201).build();

    }





    @GET
    @ApiOperation(value = "Get all Games")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGames() {

        List<Game> games = gameDAO.readAll();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get a Game given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = Game.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getGameById(@PathParam("id") int id) {

        if (gameDAO.existsId(id)) {

            Game game = gameDAO.readByParameter("id", id);
            return Response.status(200).entity(game).build();

        } else {

            return Response.status(404).build();

        }

    }




    @GET
    @ApiOperation(value = "Get a game parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readParameterById(@PathParam("id") int id,
                                             @PathParam("parameter") String parameter) {

        if (gameDAO.existsId(id)) {

            Object res = gameDAO.readParameterByParameter(parameter, "id", id);
            return Response.status(200).entity(res).build();

        } else {

            return Response.status(404).build();

        }

    }


    //UPDATE

    @PUT
    @ApiOperation(value = "Update a game by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGameById(@PathParam("id") int id, Game game) {

        if (gameDAO.existsId(id) && id == game.getId()) {

            gameDAO.update(game);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }

    }


    @PUT
    @ApiOperation(value = "Update a game parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParameterById(@PathParam("id") int id,
                                              @PathParam("parameter") String parameter,
                                              String parameterValue) {

        if (gameDAO.existsId(id)) {

            try {

                if (Game.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    gameDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id", id);

                } else {
                    gameDAO.updateParameterByParameter(parameter, parameterValue, "id", id);
                }

                return Response.status(200).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }



    // DELETE

    @DELETE
    @ApiOperation(value = "Delete a game by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Game not found"),
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id) {

        if (gameDAO.existsId(id)) {

            gameDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }

    // CREATE

    @POST
    @ApiOperation(value = "Create an enemy")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 250, message = "Enemy already exists")
    })
    @Path("/enemy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEnemyById(Enemy enemy) {

        if (!enemyDAO.exists(enemy.getName())) {

            enemyDAO.create(enemy);
            return Response.status(201).build();

        } else {

            return Response.status(250).build();
        }

    }





    @GET
    @ApiOperation(value = "Get all Enemies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Enemy.class, responseContainer="List"),
    })
    @Path("/enemy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEnemies() {

        List<Enemy> enemies = enemyDAO.readAll();

        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemies) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get an Enemy given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = Enemy.class),
            @ApiResponse(code = 404, message = "Enemy not found")
    })
    @Path("/enemy/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getEnemyById(@PathParam("id") int id) {

        if (enemyDAO.existsId(id)) {

            Enemy enemy = enemyDAO.readByParameter("id", id);
            return Response.status(200).entity(enemy).build();

        } else {

            return Response.status(404).build();

        }

    }




    @GET
    @ApiOperation(value = "Get an enemy parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/enemy/id/{id}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readEnemyParameterById(@PathParam("id") int id,
                                      @PathParam("parameter") String parameter) {

        if (enemyDAO.existsId(id)) {

            Object res = enemyDAO.readParameterByParameter(parameter, "id", id);
            return Response.status(200).entity(res).build();

        } else {

            return Response.status(404).build();

        }

    }


    //UPDATE

    @PUT
    @ApiOperation(value = "Update an enemy by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Enemy not found")
    })
    @Path("/enemy/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEnemyById(@PathParam("id") int id, Enemy enemy) {

        if (enemyDAO.existsId(id) && id == enemy.getId()) {

            enemyDAO.update(enemy);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }

    }


    @PUT
    @ApiOperation(value = "Update an enemy parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/enemy/id/{id}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEnemyParameterById(@PathParam("id") int id,
                                        @PathParam("parameter") String parameter,
                                        String parameterValue) {

        if (enemyDAO.existsId(id)) {

            try {

                if (Enemy.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    enemyDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id", id);

                } else {
                    enemyDAO.updateParameterByParameter(parameter, parameterValue, "id", id);
                }

                return Response.status(200).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }



    // DELETE

    @DELETE
    @ApiOperation(value = "Delete an enemy by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Enemy not found"),
    })
    @Path("/enemy/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEnemyById(@PathParam("id") int id) {

        if (enemyDAO.existsId(id)) {

            enemyDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }


}