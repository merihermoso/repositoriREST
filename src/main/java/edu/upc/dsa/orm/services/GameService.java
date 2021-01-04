package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;

import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.Game;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/games")
@Path("/games")
public class GameService {

    private GameDAO gameDAO;

    public GameService() {
        this.gameDAO = GameDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get all Partidas", notes = "Get all partidas from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
    })
    @Path("AllGames/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames() {

        List<Game> games = this.gameDAO.findAll();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get a Game", notes = "Get all data 1 game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")

    })

    @Path("getGame/{gameID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetGameFromId(@PathParam("gameID") int gameID) {
        try{
            Game game = this.gameDAO.getGameFromId(gameID);
            return Response.status(200).entity(game).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
}



