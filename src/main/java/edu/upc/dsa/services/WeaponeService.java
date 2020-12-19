package edu.upc.dsa.services;


import edu.upc.dsa.WeaponeManager;
import edu.upc.dsa.WeaponeManagerImpl;
import edu.upc.dsa.models.Weapone.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Weapones")
@Path("/weapone")
public class WeaponeService {

    private WeaponeManager tm;

    public WeaponeService() {

        this.tm = WeaponeManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addWeapone1();
            this.tm.addWeapone2();
            this.tm.addWeapone3();
        }

    }

    @GET
    @ApiOperation(value = "Get all Weapones", notes = "Get all Weapones from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Weapone.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeapone() {

        List<Weapone> weapones = this.tm.findAll();

        GenericEntity<List<Weapone>> entity = new GenericEntity<List<Weapone>>(weapones) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "Add a new Weapone", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Weapone registered"),
            @ApiResponse(code = 250, message = "Weapone already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hit field"),
            @ApiResponse(code = 601, message = "Need to fill in damage field")

    })
    @Path("/AddWeapone/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddWeapone1() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.WeaponeExists(username)) return Response.status(250).build();

        Weapone Weapone = this.tm.addWeapone1();
        return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a Weapone", notes = "Delete a Weapone")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Weapone found and deleted"),
            @ApiResponse(code = 404, message = "Weapone not found")
    })
    @Path("/{id}")
    public Response deleteWeapone(@PathParam("id") String id) {
        Weapone t = this.tm.getWeapone(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteWeapone(id);
        return Response.status(201).build();
    }
}