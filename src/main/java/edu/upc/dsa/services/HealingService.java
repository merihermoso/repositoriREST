package edu.upc.dsa.services;

import edu.upc.dsa.HealingManager;
import edu.upc.dsa.HealingManagerImpl;
import edu.upc.dsa.models.Healing.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/Healing")
@Path("/healing")
public class HealingService {

    private HealingManager tm;

    public HealingService() {

        this.tm = HealingManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addHealing1();
            this.tm.addHealing2();
            this.tm.addHealing3();
        }

    }

    @GET
    @ApiOperation(value = "Get all Healings", notes = "Get all Healings from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Healing.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHealing() {

        List<Healing> healings = this.tm.findAll();

        GenericEntity<List<Healing>> entity = new GenericEntity<List<Healing>>(healings) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "Add a new healing", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/AddHealing/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddHealing1() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Healing Healing = this.tm.addHealing1();
        return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a Healing", notes = "Delete a Healing")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Healing found and deleted"),
            @ApiResponse(code = 404, message = "Healing not found")
    })
    @Path("/{id}")
    public Response deleteHealing(@PathParam("id") String id) {
        Healing t = this.tm.getHealing(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteHealing(id);
        return Response.status(201).build();
    }
}