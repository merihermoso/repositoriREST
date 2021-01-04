package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.element.ElementDAO;
import edu.upc.dsa.orm.dao.element.ElementDAOImpl;
import edu.upc.dsa.orm.dao.order.OrderDAO;
import edu.upc.dsa.orm.dao.order.OrderDAOImpl;
import edu.upc.dsa.orm.models.Element;
import edu.upc.dsa.orm.models.Orders;
import edu.upc.dsa.orm.models.shopCredentials.ElementCredentials;
import edu.upc.dsa.orm.models.shopCredentials.OrderCredentials;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/shop")
@Path("shop")
public class ShopService {

    private final OrderDAO orderDAO;
    private final ElementDAO elementDAO;

    public ShopService() {

        this.orderDAO = OrderDAOImpl.getInstance();
        this.elementDAO = ElementDAOImpl.getInstance();

    }
                                                                        /////// Part ORDERS shop
    @GET
    @ApiOperation(value = "Get all Orders", notes = "Get all orders from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Orders.class, responseContainer="List"),
    })
    @Path("AllOrders/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {

        List<Orders> orders = this.orderDAO.findAll();

        GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(orders) {};
        return Response.status(201).entity(entity).build();

    }
    @GET
    @ApiOperation(value = "get an Order", notes = "Get all data 1 user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
/*
    @Path("/getOrder/{orderID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetOrderFromId(@PathParam("orderID") int orderID) {
        try{
            Orders order = this.orderDAO.getOrderById(orderID);
            return Response.status(200).entity(order).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }*/
    @Path("getOrder/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetOrderByUsername(@PathParam("username") String username) {
        try{
            Orders order = this.orderDAO.getOrderByUsername(username);
            return Response.status(200).entity(order).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    ////// Part ELEMENTS shop
    @GET
    @ApiOperation(value = "Get all Elements", notes = "Get all Elements from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Element.class, responseContainer="List"),
    })
    @Path("AllElements/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getElements(){

        List<Element> elements = this.elementDAO.findAll();

        GenericEntity<List<Element>> entity = new GenericEntity<List<Element>>(elements) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "get an Element", notes = "Get all data 1 element")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("getElement/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetElementFromUsername(@PathParam("username") String username) {
        try{
            Element element = this.elementDAO.getElementByUsername(username);
            return Response.status(200).entity(element).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    /*
    @Path("getElement/{elementID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetElementFromId(@PathParam("elementID") int elementID) {
        try{
            Element element = this.elementDAO.getElementById(elementID);
            return Response.status(200).entity(element).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }*/
    @POST
    @ApiOperation(value = "Register a new Order", notes = "Register an order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Order registered"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field"),

    })
    @Path("/registerOrder")
    public Response orderRegister(OrderCredentials orderCredentials) {

        if (orderCredentials.getDate()==null) return Response.status(600).build();
        if (orderCredentials.getTime()==null) return Response.status(601).build();
     //   if (orderCredentials.getPrice()==null) return Response.status(602).build();

        this.orderDAO.registerOrder(orderCredentials);
        return Response.status(201).build();

    }

    @POST
    @ApiOperation(value = "Register a new Element", notes = "Register an order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Order registered"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field"),

    })
    @Path("/registerElement")
    public Response elementRegister(ElementCredentials elementCredentials) {

        if (elementCredentials.getName()==null) return Response.status(600).build();
        if (elementCredentials.getDescription()==null) return Response.status(601).build();
        //   if (elementCredentials.getPrice()==null) return Response.status(602).build();

        this.elementDAO.registerElement(elementCredentials);
        return Response.status(201).build();

    }


}