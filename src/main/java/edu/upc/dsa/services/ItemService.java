package edu.upc.dsa.services;


import edu.upc.dsa.ItemManager;
import edu.upc.dsa.ItemManagerImpl;
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

@Api(value = "/Items")
@Path("/items")
public class ItemService {

    private ItemManager tm;

    public ItemService() {

        this.tm = ItemManagerImpl.getInstance();
        if (tm.size() == 0) {
            this.tm.addDefense2();
            this.tm.addDefense3();
            this.tm.addHealing3();
            this.tm.addWeapone1();
            this.tm.addWeapone3();
        }
    }

    @GET
    @ApiOperation(value = "Get all Items", notes = "Get all Items from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {

        List<Item> items = this.tm.findAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build()  ;

    }
}