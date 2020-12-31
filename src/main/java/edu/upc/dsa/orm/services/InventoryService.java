package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.InventoryManager;
import edu.upc.dsa.orm.dao.InventoryManagerImpl;
import edu.upc.dsa.orm.models.Healing.Healing;
import edu.upc.dsa.orm.models.Weapone.Weapone;
import edu.upc.dsa.orm.models.Defense.Defense;
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

    private InventoryManager tm;

    public InventoryService() {

        this.tm = InventoryManagerImpl.getInstance();
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


    @POST
    @ApiOperation(value = "Add a new Weapone1", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addWeapone1/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWeapone1() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Weapone Weapone = this.tm.addWeapone1();
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Add a new Weapone2", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addWeapone2/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWeapone2() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Weapone Weapone = this.tm.addWeapone2();
        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Add a new Weapone3", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addWeapone3/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWeapone3() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Weapone Weapone = this.tm.addWeapone3();
        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "Add a new Healing1", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addHealing1/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHealing1() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Healing Healing = this.tm.addHealing1();
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "Add a new Healing2", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addHealing2/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHealing2() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Healing Healing = this.tm.addHealing2();
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "Add a new Healing3", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Healing registered"),
            @ApiResponse(code = 250, message = "Healing already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addHealing3/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHealing3() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Healing Healing = this.tm.addHealing3();
        return Response.status(201).build();
    }



    @POST
    @ApiOperation(value = "Add a new Defense1", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Defense registered"),
            @ApiResponse(code = 250, message = "Defense already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addDefense1/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDefense1() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Defense Defense = this.tm.addDefense1();
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "Add a new Defense2", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Defense registered"),
            @ApiResponse(code = 250, message = "Defense already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addDefense2/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDefense2() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Defense Defense = this.tm.addDefense2();
        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "Add a new Defense3", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Defense registered"),
            @ApiResponse(code = 250, message = "Defense already exists"),
            @ApiResponse(code = 600, message = "Need to fill in hita field"),
            @ApiResponse(code = 601, message = "Need to fill in healing field")

    })
    @Path("/addDefense3/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDefense3() {

        //if (x==null) return Response.status(600).build();
        //if (y==null) return Response.status(601).build();

        //if (this.tm.userExists(username)) return Response.status(250).build();

        Defense Defense = this.tm.addDefense3();
        return Response.status(201).build();
    }



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