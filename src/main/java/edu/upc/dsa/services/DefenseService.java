package edu.upc.dsa.services;


import edu.upc.dsa.DefenseManager;
import edu.upc.dsa.DefenseManagerImpl;
import edu.upc.dsa.models.Defense.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Defences")
@Path("/defences")
public class DefenseService {

    private DefenseManager tm;

    public DefenseService() {

        this.tm = DefenseManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addDefense1(5, 3);
            this.tm.addDefense2(17, 2);
            this.tm.addDefense3(31, 12);
        }

    }

    @GET
    @ApiOperation(value = "Get all Defenses", notes = "Get all Defenses from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Defense.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDefense() {

        List<Defense> defences = this.tm.findAll();

        GenericEntity<List<Defense>> entity = new GenericEntity<List<Defense>>(defences) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "Add a new defence", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! User registered"),
            @ApiResponse(code = 250, message = "User already exists"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field")

    })
    @Path("/AddDefense/{hit}/{force}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddDefense1(@PathParam("hit") int hit, @PathParam("force") int force) {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Defense Defense = this.tm.addDefense1(hit, force);
        return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a Defense", notes = "Delete a Defense")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Defense found and deleted"),
            @ApiResponse(code = 404, message = "Defense not found")
    })
    @Path("/{id}")
    public Response deleteDefense(@PathParam("id") String id) {
        Defense t = this.tm.getDefense(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteDefense(id);
        return Response.status(201).build();
    }
}