package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;

import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.User;
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

@Api(value = "/games")
@Path("/games")
public class GameService {

    private GameDAO gameDAO;
    private UserDAO userDAO;

    public GameService() {
        this.gameDAO = GameDAOImpl.getInstance();
        this.userDAO = UserDAOImpl.getInstance();
    }

    @GET                                                                    //OBTENEMOS TODAS LAS PARTIDAS
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


    @GET                                                                    //OBTENEMOS las 5 PARTIDAS CON MÁS SCORE
    @ApiOperation(value = "Get TOP Games", notes = "Get top GAMES ordered BY score from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
    })
    @Path("topGames/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopGames() throws SQLException {

        List<Game> games = this.gameDAO.getGameRanking();

        GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {};
        return Response.status(201).entity(entity).build();

    }

    @GET                                                                    //OBTENEMOS las 5 PARTIDAS CON MÁS SCORE
    @ApiOperation(value = "Get TOP Users", notes = "Get top USERS ordered BY score from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("topUsers/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopUsers() throws SQLException {

        List<User> users = this.userDAO.getUserRanking();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }




    @GET                                                                    //OBTENEMOS la Partida
    @ApiOperation(value = "get a Game", notes = "Get all data 1 game")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("getGame/{username}")                                             //Partida By Username
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetGameByUsername(@PathParam("username") String username) {
        try{
            Game game = this.gameDAO.getGameByUsername(username);
            return Response.status(200).entity(game).build();
        }
        catch (Exception e){

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

}



