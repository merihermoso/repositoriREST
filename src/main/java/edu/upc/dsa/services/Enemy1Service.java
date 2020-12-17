package edu.upc.dsa.services;


import edu.upc.dsa.Enemies1Manager;
import edu.upc.dsa.Enemies1ManagerImpl;
import edu.upc.dsa.models.Enemy1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/enemies1")
@Path("/enemies1")
public class Enemy1Service {

    private Enemies1Manager tm;

    public Enemy1Service() {

        this.tm = Enemies1ManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addEnemy1(5, 3);
            this.tm.addEnemy1(17, 2);
            this.tm.addEnemy1(31, 12);
        }

    }

    @GET
    @ApiOperation(value = "Get all Enemies1", notes = "Get all enemies from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy1.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies1() {

        List<Enemy1> enemies1 = this.tm.findAll();

        GenericEntity<List<Enemy1>> entity = new GenericEntity<List<Enemy1>>(enemies1) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
