package edu.upc.dsa.services;


import edu.upc.dsa.PartidasManager;
import edu.upc.dsa.PartidasManagerImpl;
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

    private PartidasManager pm;

    public PartidasService() {

        this.pm = PartidasManagerImpl.getInstance();
        if (pm.size()==0) {
            this.pm.addPartida( 20000);
            this.pm.addPartida(30000);
            this.pm.addPartida(80000);
            this.pm.addPartida(70000);
        }
    }

    @GET
    @ApiOperation(value = "Get all Partidas", notes = "Get all partidas from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Partida.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPartidas() {

        List<Partida> partidas = this.pm.findAll();

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
    public Response deletePartida(@PathParam("id") String id_partida) {
        Partida p = this.pm.getPartida(id_partida);
        if (p == null) return Response.status(404).build();
        else this.pm.deletePartida(id_partida);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Update a User", notes = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updatePartida(Partida partida) {

        Partida p = this.pm.updatePartida(partida);

        if (p == null) return Response.status(404).build();

        return Response.status(201).build();
    }
/*
    @POST
    @ApiOperation(value = "Register a new Partida", notes = "Register a Partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Partida registered"),
            @ApiResponse(code = 600, message = "Need to fill in score field"),
          //  @ApiResponse(code = 601, message = "Need to fill in password field"),
            @ApiResponse(code = 250, message = "User already exists")

    })

    @Path("/register/{id_partida}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("id_partida") String id_partida) {

        if (id_partida==null) return Response.status(600).build();

        if (this.pm.partidaExists(id_partida)) return Response.status(250).build();

        Partida pa = this.pm.addPartida(id_partida);
        return Response.status(201).build();
    }

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
    public Response partidaLogin(@PathParam("username") String username, @PathParam("password") String password) {

        if (username==null) return Response.status(601).build();
        if (password==null) return Response.status(602).build();
        if (!this.pm.partidaExists(username)) return Response.status(250).build();
       // if (!this.pm.checkPassword(username, password)) return Response.status(603).build();

        return Response.status(201).build();
    }*/

}