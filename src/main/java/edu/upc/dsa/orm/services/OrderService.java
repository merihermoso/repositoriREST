package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.order.OrderDAO;
import edu.upc.dsa.orm.dao.order.OrderDAOImpl;
import edu.upc.dsa.orm.models.Orders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/order")
@Path("order")
public class OrderService {

    private final OrderDAO orderDAO;

    public OrderService() {

        this.orderDAO = OrderDAOImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "Get all Orders", notes = "Get all orders from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Orders.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {

        List<Orders> orders = this.orderDAO.findAll();

        GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(orders) {};
        return Response.status(201).entity(entity).build();

    }

}