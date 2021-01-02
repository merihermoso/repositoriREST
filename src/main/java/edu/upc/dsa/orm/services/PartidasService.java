package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.partida.PartidaDAO;
import edu.upc.dsa.orm.dao.partida.PartidaDAOImpl;

import edu.upc.dsa.orm.models.Partida;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/partidas")
@Path("/partidas")
public class PartidasService {

    private PartidaDAO pm;

    public PartidasService() {

      //  this.pm = PartidaDAOImpl.getInstance();                       DA ERROR
       /* if (pm.size()==0) {                           //per afegir les partides si no estaven a la bbdd
            this.pm.addPartida(20000);
            this.pm.addPartida(30000);
            this.pm.addPartida(80000);
            this.pm.addPartida(70000);
        }*/
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



    @DELETE
    @ApiOperation(value = "Delete a Partida", notes = "Delete a partida")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Partida found and deleted"),
            @ApiResponse(code = 404, message = "Partida not found")
    })
    @Path("/{id}")
    public Response deletePartida(@PathParam("id") int partidaID) {
        Partida p = this.pm.getPartida(partidaID);
        if (p == null) return Response.status(404).build();
        else this.pm.deletePartida(partidaID);
        return Response.status(201).build();
    }
/*                                                                          DONA ERROR AL FER UPDATE DE TOT L'OBJECTE PARTIDA
    @PUT
    @ApiOperation(value = "Update Partida", notes = "Update a partida")
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
*/
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
    public Response newUser(@PathParam("id_partida") int partidaID) {

       // if (partidaID==null) return Response.status(600).build();      //crec que l'error seria 204 "NO CONTENT"

       // if (this.pm.partidaExists(id_partida)) return Response.status(250).build();

       // Partida pa = this.pm.addPartida(partidaID);       //LA FUNCIÓ NECESITA TOTS ELS PARÀMETRES PER CREAR PARTIDA
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
     //   if (!this.pm.partidaExists(username)) return Response.status(250).build();        //LA FUNCIÓ NO ESTÀ IMPLEMENTADA
       // if (!this.pm.checkPassword(username, password)) return Response.status(603).build();

        return Response.status(201).build();
    }

}



