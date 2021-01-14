package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.eclipse.persistence.annotations.Convert;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/item")
@Path("item")
public class ItemService {


    private final ItemDAO itemDAO;

    public ItemService() {

        this.itemDAO = ItemDAOImpl.getInstance();

    }

/*
    //Servicio para obtener todos los items
    @GET
    @ApiOperation(value = "Get all items from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Item> items = this.itemDAO.readAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {
        };
        return Response.status(201).entity(entity).build();
    }

*/
    //Servicio para obtener un Item a partir del ID
    @GET
    @ApiOperation(value = "get an Item by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readItemById(@PathParam("id") int id) {

        Item item = itemDAO.readByParameter("id", id);

        System.out.println(item.toString());

        if (item == null) {
            return Response.status(404).build();
        } else {
            return Response.status(200).entity(item).build();
        }

    }

/*
    //Servei per obtenir un item a partir del seu nom
    @GET
    @ApiOperation(value = "get an Item by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/{itemName}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemByName(@PathParam("name") String name) {
        try{
            Item item = this.itemDAO.readByParameter("name", name);
            return Response.status(200).entity(item).build();
        }
        catch (Exception e){
            return Response.status(503).build();
        }
    }


    @GET
    @ApiOperation(value = "Get an item parameter by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/{name}/parameter/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIdByName(@PathParam("name") String name, @PathParam("parameter") String parameter) {

        Object res = itemDAO.readParameterByParameter(parameter, "name", name);

        if (res != null){

            return Response.status(201).entity(res).build();

        } else {

            return Response.status(404).build();
        }

    }

*/
    /*
    @PUT
    @ApiOperation(value = "Update an item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "not found")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateItem(Item item) {
        if (itemDAO.update(item)) {
            return Response.status(200).build();
        }
        else{
            return Response.status(400).build();
        }

    }*/

}



