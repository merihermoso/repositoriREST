package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.inventory.InventoryDAO;
import edu.upc.dsa.orm.dao.inventory.InventoryDAOImpl;
import edu.upc.dsa.orm.dao.item.ItemDAO;
import edu.upc.dsa.orm.dao.item.ItemDAOImpl;
import edu.upc.dsa.orm.dao.orders.OrdersDAO;
import edu.upc.dsa.orm.dao.orders.OrdersDAOImpl;
import edu.upc.dsa.orm.models.API.UpdateParameterRequest;
import edu.upc.dsa.orm.models.Inventory;
import edu.upc.dsa.orm.models.Item;
import edu.upc.dsa.orm.models.Orders;
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
import java.util.List;


@Api(value = "/orders")
@Path("orders")
public class OrdersService {

    private final ItemDAO itemDAO;
    private final OrdersDAO ordersDAO;


    public OrdersService() {

        itemDAO = ItemDAOImpl.getInstance();
        ordersDAO = OrdersDAOImpl.getInstance();

    }


/****************************************** orders service ************************************************************/


    // CREATE

    @POST
    @ApiOperation(value = "Add an item to Order (SHOP)")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 250, message = "Item already exists")       //aumentem quantitat?
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrdersById(Orders orders) {

        ordersDAO.create(orders);
        return Response.status(201).build();

    }


    @GET
    @ApiOperation(value = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Orders.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {

        List<Orders> orders = ordersDAO.readAll();

        GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(orders) {};
        return Response.status(200).entity(entity).build();

    }



    @GET
    @ApiOperation(value = "Get an order given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Orders.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Game not found")
    })
    @Path("/id/{id}/order")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") int id) {

        if (ordersDAO.existsId(id)) {

            List<Orders> ordersItems = ordersDAO.readAllByParameter("id_user", id);

            GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(ordersItems) {
            };
            return Response.status(200).entity(entity).build();

        } else {
            return Response.status(404).build();
        }

    }


    //UPDATE

    @PUT
    @ApiOperation(value = "Update an order by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Order not found")
    })
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrderById(@PathParam("id") int userID, Orders orders) {

        ordersDAO.update(orders);
        return Response.status(200).build();

    }



    @PUT
    @ApiOperation(value = "Update an order parameter by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Item not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderParameterById(@PathParam("id") int userID,
                                             @PathParam("parameter") String parameter,
                                             UpdateParameterRequest updateParameterRequest) {

        String parameterValue = updateParameterRequest.getParameterValue();

        if (itemDAO.existsId(userID)) {

            try {

                if (Inventory.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    ordersDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id_user", userID);

                } else {
                    ordersDAO.updateParameterByParameter(parameter, parameterValue, "id_user", userID);
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
    @ApiOperation(value = "Delete an order by its userID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Order not found"),
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderById(@PathParam("id") int userID) {

        if (ordersDAO.existsId(userID)) {

            ordersDAO.deleteByParameter("id_user", userID);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }


}