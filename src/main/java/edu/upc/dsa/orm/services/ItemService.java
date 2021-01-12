package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.enemy.EnemyDAO;
import edu.upc.dsa.orm.dao.enemy.EnemyDAOImpl;
import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;
import edu.upc.dsa.orm.dao.inventory.InventoryDAO;
import edu.upc.dsa.orm.dao.inventory.InventoryDAOImpl;
import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.dao.player.PlayerDAO;
import edu.upc.dsa.orm.dao.player.PlayerDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.exeptions.UserNotFoundException;
import edu.upc.dsa.orm.models.API.ProfileResponse;
import edu.upc.dsa.orm.models.API.RankingPositionResponse;
import edu.upc.dsa.orm.models.*;
import edu.upc.dsa.orm.models.GameCredentials.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/item")
@Path("item")
public class ItemService {


    private final ItemDAO itemDAO;
    private final InventoryDAO inventoryDAO;

    public ItemService() {

        this.inventoryDAO = InventoryDAOImpl.getInstance();
        this.itemDAO = ItemDAOImpl.getInstance();
    }

    /*********************************************  resum    *******************************************************/
    /***
     * /Items/findAll
     * /Item/GetByID/{itemID}
     * /Item/getByNAME/{name}
     * /Item/register
     *
     /*********************************************  falta per fer    *******************************************************/

    //ordenar per preu



/******************************************     ITEMS services  *******************************************************/
    //Servicio para obtener todos los items
    @GET
    @ApiOperation(value = "Get all items from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "List"),
    })
    @Path("/Items/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {

        List<Item> items = this.itemDAO.findAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {
        };
        return Response.status(201).entity(entity).build();
    }


    //Servicio que devuelve una lista con los objetos del jugador

    @GET
    @ApiOperation(value = "get items from user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Inventory.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @Path("/itemsPlayer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsPlayer(@QueryParam("username") String username) throws UserNotFoundException {

        HashMap<Integer,Inventory> items = null;
        List<Inventory> o = new LinkedList<>();

        items = this.itemDAO.getItemsUser(username);
        for ( Integer key : items.keySet() ) {
            o.add(items.get(key));
        }

        GenericEntity<List<Inventory>> entity = new GenericEntity<List<Inventory>>(o) {};

        if(entity==null) return Response.status(500).build();
        return Response.status(200).entity(entity).build();
    }


    //Servicio para obtener un Item a partir del ID
    @GET
    @ApiOperation(value = "get an Item by its ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/Item/GetByID/{itemID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemById(@PathParam("itemID") int itemID) {
        try {
            Item item = this.itemDAO.getItemById(itemID);
            return Response.status(200).entity(item).build();
        } catch (Exception e) {
            return Response.status(503).build();
        }
    }


    //Servei per obtenir un item a partir del seu nom
    @GET
    @ApiOperation(value = "get an Item by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Item.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/Item/getByNAME/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetItemByName(@PathParam("name") String name) {
        try{
            Item item = this.itemDAO.getItemByName(name);
            return Response.status(200).entity(item).build();
        }
        catch (Exception e){
            return Response.status(503).build();
        }
    }



    //Servei per obtenir l'ID d'un Item a partir del seu nom
    @POST
    @ApiOperation(value = "Get an item ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = ObjectIdResponse.class),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/Item/getIdByName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdByName(GetItemCredentials getItemCredentials) throws SQLException {
        ObjectIdResponse objectIdResponse = new ObjectIdResponse(this.itemDAO.getIdByName(getItemCredentials.getName()));
        if (objectIdResponse.getObjectID() != -1) {
            return Response.status(201).entity(objectIdResponse).build();
        } else {
            return Response.status(404).entity(objectIdResponse).build();
        }
    }


    //Servei per registrar un nou item
    @POST
    @ApiOperation(value = "Register a new Item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Game registered"),
    })
    @Path("/Item/register")
    public Response itemRegister(ItemCredentials itemCredentials) throws SQLException, IllegalAccessException {
        this.itemDAO.registerItem(itemCredentials);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Update an item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "Exception sql..."),
            @ApiResponse(code = 400, message = "not found")
    })
    @Path("/Item/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateItem(Item item) {
        try{
            int res = itemDAO.updateItem(item);
            if (res==0) {
                return Response.status(200).build();
            }
            else{
                return Response.status(400).build();
            }
        }
        catch (Exception e){
            return Response.status(503).build();
        }
    }

    /********************************************** inventory  *****************************************/

    //Servicio para obtener todos los items
    @GET
    @ApiOperation(value = "Get all items from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer = "List"),
    })
    @Path("/Inventory/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventory() {

        List<Inventory> inventory = this.inventoryDAO.findAll();

        GenericEntity<List<Inventory>> entity = new GenericEntity<List<Inventory>>(inventory) {
        };
        return Response.status(201).entity(entity).build();
    }
    @PUT
    @ApiOperation(value = "Update inventory")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "Exception sql..."),
            @ApiResponse(code = 400, message = "not found")
    })
    @Path("/Inventory/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateInventory(Inventory inventory) {
        try{
            int res = inventoryDAO.updateInventory(inventory);
            if (res==0) {
                return Response.status(200).build();
            }
            else{
                return Response.status(400).build();
            }
        }
        catch (Exception e){
            return Response.status(503).build();
        }
    }

}



