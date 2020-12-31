package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.EnemyManager;
import edu.upc.dsa.orm.dao.EnemyManagerImpl;
import edu.upc.dsa.orm.models.Enemy.*;

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
    @ApiOperation(value = "Get all Enemies", notes = "Get all enemies from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Enemy.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEnemies() {

        List<Enemy> enemies = this.tm.findAll();

        GenericEntity<List<Enemy>> entity = new GenericEntity<List<Enemy>>(enemies) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @POST
    @ApiOperation(value = "Add a new enemy", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! User registered"),
            @ApiResponse(code = 250, message = "User already exists"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field")

    })
    @Path("/AddEnemy/{x}/{y}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddEnemy1(@PathParam("x") int x, @PathParam("y") int y) {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Enemy enemy = this.tm.addEnemy1(x, y);
        return Response.status(201).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a Enemy", notes = "Delete a enemy")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Enemy found and deleted"),
            @ApiResponse(code = 404, message = "Enemy not found")
    })
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        Enemy t = this.tm.getEnemy(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteEnemy(id);
        return Response.status(201).build();
    }
}
