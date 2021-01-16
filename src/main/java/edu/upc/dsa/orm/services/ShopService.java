package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.inventory.InventoryDAO;
import edu.upc.dsa.orm.dao.inventory.InventoryDAOImpl;
import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.API.*;
import edu.upc.dsa.orm.models.Inventory;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/shop")
@Path("shop")
public class ShopService {

    private final ItemDAO itemDAO;
    private final InventoryDAO inventoryDAO;


    public ShopService() {

        itemDAO = ItemDAOImpl.getInstance();
        inventoryDAO = InventoryDAOImpl.getInstance();

    }

    // CREATE

    @POST
    @ApiOperation(value = "Create an item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 250, message = "Item already exists")
    })
    @Path("/item/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItemById(Item item) {

        if (!itemDAO.exists(item.getName())) {

            itemDAO.create(item);
            return Response.status(201).build();

        } else {

            return Response.status(250).build();
        }

    }



    @GET
    @ApiOperation(value = "Get all Items")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllItems() {

        List<Item> items = itemDAO.readAll();

        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get a Item given its name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "User not exists"),

    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemByName(@PathParam("name") String name) {

        if (itemDAO.exists(name)) {

            Item item = itemDAO.readByParameter("name", name);

            return Response.status(200).entity(item).build();

        } else {

            return Response.status(404).build();

        }

    }








    @GET
    @ApiOperation(value = "Get a Item given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = Item.class),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getItemById(@PathParam("id") int id) {

        if (itemDAO.existsId(id)) {

            Item item = itemDAO.readByParameter("id", id);
            return Response.status(200).entity(item).build();

        } else {

            return Response.status(404).build();

        }

    }




    @GET
    @ApiOperation(value = "Get an item parameter by its name ( no obtiene algunos valores int ) ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Parameter not found"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/{name}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readParameterByName(@PathParam("name") String name,
                                            @PathParam("parameter") String parameter) {

        if (itemDAO.exists(name)) {

            try {

                Field field = Item.class.getDeclaredField(parameter);

                Object res = itemDAO.readParameterByParameter(parameter, "username", name);
                return Response.status(200).entity(res).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get an item parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readParameterById(@PathParam("id") int id,
                                             @PathParam("parameter") String parameter) {

        if (itemDAO.existsId(id)) {

            Object res = itemDAO.readParameterByParameter(parameter, "id", id);
            return Response.status(200).entity(res).build();

        } else {

            return Response.status(404).build();

        }

    }


    //UPDATE

    @PUT
    @ApiOperation(value = "Update an item by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Item not found")
    })
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItemById(@PathParam("id") int id, Item item) {

        if (itemDAO.existsId(id) && id == item.getId()) {

            itemDAO.update(item);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }

    }

    @PUT
    @ApiOperation(value = "Update an item by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateItemByName(@PathParam("name") String name, Item item) {

        if (itemDAO.exists(name) && name.equals(item.getName())) {

            itemDAO.updateByParameter(item,"name", name);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }


    }


    @PUT
    @ApiOperation(value = "Update an item parameter by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/{name}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParameterByName(@PathParam("name") String name,
                                              @PathParam("parameter") String parameter,
                                              String parameterValue) {

        if (itemDAO.exists(name)) {

            try {

                if (Item.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    itemDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "name", name);

                } else {
                    itemDAO.updateParameterByParameter(parameter, parameterValue, "name", name);
                }

                return Response.status(200).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }


    @PUT
    @ApiOperation(value = "Update an item parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParameterById(@PathParam("id") int id,
                                              @PathParam("parameter") String parameter,
                                              String parameterValue) {

        if (itemDAO.existsId(id)) {

            try {

                if (Item.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    itemDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id", id);

                } else {
                    itemDAO.updateParameterByParameter(parameter, parameterValue, "id", id);
                }

                return Response.status(200).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }



    // DELETE

    @DELETE
    @ApiOperation(value = "Delete an item by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id) {

        if (itemDAO.existsId(id)) {

            itemDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }


    @DELETE
    @ApiOperation(value = "Delete an item by its name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
    })
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByName(@PathParam("name") String name) {

        if (itemDAO.exists(name)) {

            int id = (int) itemDAO.readParameterByParameter("id", "name", name);

            itemDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }

    /**************************************  inventory service  *******************************************************/

    // CREATE

    @POST
    @ApiOperation(value = "Add an item to Inventory")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 250, message = "Item already exists")       //aumentem quantitat?
    })
    @Path("/inventory")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInventoryById(Inventory inventory) {

        inventoryDAO.create(inventory);
        return Response.status(201).build();

    }



    @GET
    @ApiOperation(value = "Get all inventories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Inventory.class, responseContainer="List"),
    })
    @Path("/inventory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInventories() {

        List<Inventory> inventories = inventoryDAO.readAll();

        GenericEntity<List<Inventory>> entity = new GenericEntity<List<Inventory>>(inventories) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get an inventory given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = Item.class),
            @ApiResponse(code = 404, message = "Inventory not found")
    })
    @Path("/inventory/id/{userID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getInventoryById(@PathParam("userID") int userID) {

        if (itemDAO.existsId(userID)) {

            Item item = itemDAO.readByParameter("userID", userID);
            return Response.status(200).entity(item).build();

        } else {

            return Response.status(404).build();

        }

    }

    /*
    @GET                                                                                            // DONA ERROR
    @ApiOperation(value = "Get an inventory parameter by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/id/{userID}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readParameterById(@PathParam("userID") int userID,
                                      @PathParam("parameter") String parameter) {

        if (itemDAO.existsId(userID)) {

            Object res = itemDAO.readParameterByParameter(parameter, "userID", userID);
            return Response.status(200).entity(res).build();

        } else {

            return Response.status(404).build();

        }

    }
*/

    //UPDATE

    @PUT
    @ApiOperation(value = "Update an inventory by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Inventory not found")
    })
    @Path("/inventory/id/{userID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInventoryById(@PathParam("userID") int userID, Inventory inventory) {

        inventoryDAO.update(inventory);
        return Response.status(200).build();

    }



    @PUT
    @ApiOperation(value = "Update an inventory parameter by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/inventory/id/{userID}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventoryParameterById(@PathParam("userID") int userID,
                                        @PathParam("parameter") String parameter,
                                        String parameterValue) {

        if (itemDAO.existsId(userID)) {

            try {

                if (Inventory.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    inventoryDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "userID", userID);

                } else {
                    inventoryDAO.updateParameterByParameter(parameter, parameterValue, "userID", userID);
                }

                return Response.status(200).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }



    // DELETE

    @DELETE
    @ApiOperation(value = "Delete an inventory by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Inventory not found"),
    })
    @Path("/inventory/id/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInventoryById(@PathParam("userID") int userID) {

        if (inventoryDAO.existsId(userID)) {

            inventoryDAO.deleteByParameter("userID", userID);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }



}