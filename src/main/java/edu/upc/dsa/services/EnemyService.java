package edu.upc.dsa.services;


import edu.upc.dsa.EnemyManager;
import edu.upc.dsa.EnemyManagerImpl;
import edu.upc.dsa.models.Enemy.Enemy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/enemies")
@Path("/enemies")
public class EnemyService {

    private EnemyManager tm;

    public EnemyService() {

        this.tm = EnemyManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addEnemy1(5, 3);
            this.tm.addEnemy2(17, 2);
            this.tm.addEnemy3(31, 12);
        }

    }

    @GET
    @ApiOperation(value = "Get all Enemies1", notes = "Get all enemies from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies1() {

        List<Enemy> enemies = this.tm.findAll();

        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemies) {};
        return Response.status(201).entity(entity).build()  ;

    }
}
