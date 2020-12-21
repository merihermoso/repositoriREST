package edu.upc.dsa.services;


import edu.upc.dsa.ChestManager;
import edu.upc.dsa.ChestManagerImpl;
import edu.upc.dsa.InventoryManager;
import edu.upc.dsa.InventoryManagerImpl;
import edu.upc.dsa.models.Chest;
import edu.upc.dsa.models.Enemy.Enemy;
import edu.upc.dsa.models.Healing.Healing;
import edu.upc.dsa.models.Weapone.Weapone;
import edu.upc.dsa.models.Defense.Defense;
import edu.upc.dsa.models.Item;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
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