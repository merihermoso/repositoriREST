package edu.upc.dsa.orm.services;

/*
import edu.upc.dsa.orm.dao.ChestManager;
import edu.upc.dsa.orm.dao.ChestManagerImpl;

import io.swagger.annotations.Api;

import javax.ws.rs.*;

@Api(value = "/Chest")
@Path("/items")
public class ChestService {

    private ChestManager tm;

    public ChestService() {

        this.tm = ChestManagerImpl.getInstance();
        if (tm.size() == 0) {
            this.tm.addDefense2();
            this.tm.addDefense3();
            this.tm.addHealing3();
            this.tm.addWeapone1();
            this.tm.addWeapone3();
        }
    }

    /*@GET
    @ApiOperation(value = "Get all Enemies", notes = "Get all enemies from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Chest.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {

        List<Item> items = this.tm.findAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build();

    }
}


 */