package edu.upc.dsa.services;


import edu.upc.dsa.UsersManager;
import edu.upc.dsa.UsersManagerImpl;
import edu.upc.dsa.models.Partida;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/partidas")
@Path("/partidas")
public class PartidasService {

    private UsersManager tm;

    public PartidasService() {
        this.tm = UsersManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addPartida(10000);
            this.tm.addPartida(60000);
            this.tm.addPartida(90000);

        }

    }

    @GET
    @ApiOperation(value = "Get all Partidas", notes = "Get all partidas from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartida() {

        List<Partida> partidas = this.tm.findAllp();

        GenericEntity<List<Partida>> entity = new GenericEntity<List<Partida>>(partidas) {};
        return Response.status(201).entity(entity).build()  ;

    }

    //prueba commit
/*
    @GET
    @ApiOperation(value = "Get a User", notes = "Get a user from DNI")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User u = this.tm.getUser(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }*/

    @DELETE
    @ApiOperation(value = "Delete a Partida", notes = "Delete a partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida found and deleted"),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/{id}")
    public Response deletePartida(@PathParam("id_partida") String id_partida) {
        Partida t = this.tm.getPartida(id_partida);
        if (t == null) return Response.status(404).build();
        else this.tm.deletePartida(id_partida);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Update a Partida", notes = "Update a partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida found"),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/")
    public Response updatePartida(Partida partida) {

        Partida p = this.tm.updatePartida(partida);

        if (p == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Register a new Partida", notes = "Register a partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Partida registered"),
            @ApiResponse(code = 600, message = "Need to fill in score field"),
            @ApiResponse(code = 250, message = "Partida already exists")

    })
    @Path("/register/{score_partida}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("score_partida") int score_partida) {

        if (score_partida==0) return Response.status(600).build();

     //   if (this.tm.partidaExists(id_partida)) return Response.status(250).build();

        Partida pa = this.tm.addPartida(score_partida);
        return Response.status(201).build();
    }
/*                                                          //Adaptar para saber usuario-partida
    @GET
    @ApiOperation(value = "A user tries to login", notes = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Login Successful!"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 603, message = "Incorrect password"),
            @ApiResponse(code = 250, message = "User not exists")

    })
    @Path("/login/{username}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogin(@PathParam("username") String username, @PathParam("password") String password) {

        if (username==null) return Response.status(601).build();
        if (password==null) return Response.status(602).build();
        if (!this.tm.userExists(username)) return Response.status(250).build();
        if (!this.tm.checkPassword(username, password)) return Response.status(603).build();

        return Response.status(201).build();
    }*/

}