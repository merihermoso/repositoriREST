package edu.upc.dsa.services;


import edu.upc.dsa.EnemiesManager;
import edu.upc.dsa.EnemiesManagerImpl;
import edu.upc.dsa.models.Enemy.Enemy1;

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
public class EnemyService {

    private EnemiesManager tm;

    public EnemyService() {

        this.tm = EnemiesManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addEnemy(5, 3);
            this.tm.addEnemy(17, 2);
            this.tm.addEnemy(31, 12);
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
