package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.inventory.InventoryDAO;
import edu.upc.dsa.orm.dao.inventory.InventoryDAOImp;
import edu.upc.dsa.orm.models.Item;

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
public class InventoryService {

    private InventoryDAO tm;

    public InventoryService() {

        this.tm = InventoryDAOImp.getInstance();
        if (tm.size() == 0) {
            this.tm.addItem(new Item("Defense", 1, 1, 7, 44));
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


    @POST
    @ApiOperation(value = "Add a new Item", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Item registered"),
            @ApiResponse(code = 250, message = "Item already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addItem/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@PathParam("objeto") String objeto)
        {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        this.tm.addItem(new Item("objeto", 1, 2, 3, 4));
        return Response.status(201).build();
    } //Terminar de arreglar para poder pasar parametros


    @DELETE
    @ApiOperation(value = "Delete an Item", notes = "Item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Item found and deleted"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/{id}")
    public Response deleteItem(@PathParam("id") String id) {
        Item t = this.tm.getItem(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteItem(id);
        return Response.status(201).build();
    }
}