package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.entity.EntityDAO;
import edu.upc.dsa.orm.dao.entity.EntityDAOImpl;
import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;
import edu.upc.dsa.orm.dao.inventory.InventoryDAO;
import edu.upc.dsa.orm.dao.inventory.InventoryDAOImpl;
import edu.upc.dsa.orm.dao.map.MapDAO;
import edu.upc.dsa.orm.dao.map.MapDAOImpl;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.API.GameSettings;
import edu.upc.dsa.orm.models.API.UpdateParameterRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/game")
@Path("game")
public class GameService {

    private final GameDAO gameDAO;
    private final MapDAO mapDAO;
    private final EntityDAO entityDAO;
    private final InventoryDAO inventoryDAO;

    public GameService() {

        gameDAO = GameDAOImpl.getInstance();
        mapDAO = MapDAOImpl.getInstance();
        entityDAO = EntityDAOImpl.getInstance();
        inventoryDAO = InventoryDAOImpl.getInstance();

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
    @ApiOperation(value = "Get all game maps")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Map.class, responseContainer="List"),
    })
    @Path("/map")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMaps() {

        List<Map> maps = mapDAO.readAll();

        GenericEntity<List<Map>> entity = new GenericEntity<List<Map>>(maps) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get game settings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = GameSettings.class),
    })
    @Path("/settings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameSettings() {

        return Response.status(200).entity(gameDAO.readSettings()).build();

    }


    @GET
    @ApiOperation(value = "Get a Game given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = Game.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/{id}")
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
    @Path("/{id}/{parameter}")
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
    @Path("/{id}")
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
    @Path("/{id}/{parameter}")
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
    @Path("/{id}")
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
    @ApiOperation(value = "Create an entity")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 250, message = "Entity already exists")
    })
    @Path("/entity")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEntityById(Entity entity) {

        if (!entityDAO.exists(entity.getName())) {

            entityDAO.create(entity);
            return Response.status(201).build();

        } else {

            return Response.status(250).build();
        }

    }





    @GET
    @ApiOperation(value = "Get all Enemies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Entity.class, responseContainer="List"),
    })
    @Path("/entity")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEnemies() {

        List<Entity> enemies = entityDAO.readAll();

        GenericEntity<List<Entity>> entity = new GenericEntity<List<Entity>>(enemies) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get an Entity given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = Entity.class),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/entity/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getEntityById(@PathParam("id") int id) {

        if (entityDAO.existsId(id)) {

            Entity entity = entityDAO.readByParameter("id", id);
            return Response.status(200).entity(entity).build();

        } else {

            return Response.status(404).build();

        }

    }




    @GET
    @ApiOperation(value = "Get an entity parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/entity/id/{id}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readEntityParameterById(@PathParam("id") int id,
                                      @PathParam("parameter") String parameter) {

        if (entityDAO.existsId(id)) {

            Object res = entityDAO.readParameterByParameter(parameter, "id", id);
            return Response.status(200).entity(res).build();

        } else {

            return Response.status(404).build();

        }

    }




    //UPDATE

    @PUT
    @ApiOperation(value = "Update an entity by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Entity not found")
    })
    @Path("/entity/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntityById(@PathParam("id") int id, Entity entity) {

        if (entityDAO.existsId(id) && id == entity.getId()) {

            entityDAO.update(entity);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }

    }


    @PUT
    @ApiOperation(value = "Update an entity parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Game not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/entity/id/{id}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEntityParameterById(@PathParam("id") int id,
                                        @PathParam("parameter") String parameter,
                                        String parameterValue) {

        if (entityDAO.existsId(id)) {

            try {

                if (Entity.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    entityDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id", id);

                } else {
                    entityDAO.updateParameterByParameter(parameter, parameterValue, "id", id);
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
    @ApiOperation(value = "Delete an entity by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Entity not found"),
    })
    @Path("/entity/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEntityById(@PathParam("id") int id) {

        if (entityDAO.existsId(id)) {

            entityDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }
/**********************************     Inventory service       *******************************************************/

@GET
@ApiOperation(value = "Get a game inventory given its id")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful", response = Inventory.class, responseContainer="List"),
        @ApiResponse(code = 404, message = "Game not found")
})
@Path("/{id}/inventory")
@Produces(MediaType.APPLICATION_JSON)
public Response getGameInventory(@PathParam("id") int id) {

    if (gameDAO.existsId(id)) {

        List<Inventory> inventoryItems = inventoryDAO.readAllByParameter("id_game", id);

        GenericEntity<List<Inventory>> entity = new GenericEntity<List<Inventory>>(inventoryItems) {
        };
        return Response.status(200).entity(entity).build();

    } else {
        return Response.status(404).build();
    }

}


    //UPDATE

    @PUT
    @ApiOperation(value = "Update a game inventory by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Inventory not found")
    })
    @Path("/{id}/inventory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInventoryById(@PathParam("id") int gameID, Inventory inventory) {

        inventoryDAO.update(inventory);
        return Response.status(200).build();

    }



    @PUT
    @ApiOperation(value = "Update an inventory parameter by its gameID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Inventory not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/{id}/{parameter}/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryParameterById(@PathParam("id") int gameID,
                                             @PathParam("parameter") String parameter,
                                             String parameterValue) {

        if (inventoryDAO.existsId(gameID)) {

            try {

                if (Inventory.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    inventoryDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id_game", gameID);

                } else {
                    inventoryDAO.updateParameterByParameter(parameter, parameterValue, "id_user", gameID);
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
    @ApiOperation(value = "Delete an inventory by its gameID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Inventory not found"),
    })
    @Path("/{id}/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderById(@PathParam("id") int gameID) {

        if (inventoryDAO.existsId(gameID)) {

            inventoryDAO.deleteByParameter("id_game", gameID);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }

}