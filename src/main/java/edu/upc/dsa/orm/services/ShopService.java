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
/*******************************************    ORDERS services    *******************************************************/
    //Servei per obtenir totes les comandes
    @GET
    @ApiOperation(value = "Get all Orders", notes = "Get all orders from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Orders.class, responseContainer="List"),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/Orders/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() {
        try{
        List<Orders> orders = this.orderDAO.findAll();

        GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(orders) {};
        return Response.status(201).entity(entity).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    //Servei per obtenir LA PRIMERA comanda de un usuari                                //hauria de retornar totes
    @GET
    @ApiOperation(value = "get an Order by Username", notes = "Get all data 1 user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well..."),
            @ApiResponse(code = 600, message = "Need to fill in username field.")

    })
    @Path("/Order/getByUSERNAME/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetOrderByUsername(@PathParam("username") String username) {
        try{
            if (username==null) return Response.status(600).build();
            Orders order = this.orderDAO.getOrderByUsername(username);
            return Response.status(200).entity(order).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    //Servei per obtenir una comanda a partir de la seva ID
    @GET                                                                //Servicio para obtener un Pedido a partir del ID
    @ApiOperation(value = "get an Order from its ID", notes = "Get all data 1 user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well..."),
            @ApiResponse(code = 600, message = "Need to fill in orderID field.")

    })
    @Path("/Order/getByID/{orderID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetOrderFromId(@PathParam("orderID") int orderID) {
        try{
            if (orderID==0) return Response.status(600).build();
            Orders order = this.orderDAO.getOrderById(orderID);
            return Response.status(200).entity(order).build();
        }
        catch (Exception e){
            return Response.status(503).build();
        }
    }

    /*******************************************    ELEMENTS services    *******************************************************/
    //Servei per obtenir tots els elements registrats
    @GET                                                                            //Servicio para obtener todos los elementos
    @ApiOperation(value = "Get all Elements from BBDD", notes = "Get all Elements from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Element.class, responseContainer="List"),
            @ApiResponse(code = 503, message = "not working well..."),
    })
    @Path("/Element/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getElements(){
        try{
        List<Element> elements = this.elementDAO.findAll();

        GenericEntity<List<Element>> entity = new GenericEntity<List<Element>>(elements) {};
        return Response.status(201).entity(entity).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    // Servei per obtenir un element a partir del username del usuari
    @GET                                                                            //Servicio para obtener el elemento de un Usuario (USERNAME)
    @ApiOperation(value = "get an Element by Username", notes = "Get all data 1 element")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well..."),
            @ApiResponse(code = 600, message = "Need to fill in username field.")

    })
    @Path("/Element/getByUSERNAME/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetElementFromUsername(@PathParam("username") String username) {
        try{
            if (username==null) return Response.status(600).build();
            Element element = this.elementDAO.getElementByUsername(username);
            return Response.status(200).entity(element).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    //Servei per obtenir un element a partir de la seva id
    @GET                                                                            //Servicio para obtener un elemento a partir de su ID
    @ApiOperation(value = "get an Element by its ID", notes = "Get all data 1 element")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well..."),
            @ApiResponse(code = 600, message = "Need to fill in elementID field.")

    })
    @Path("/Element/getByID/{elementID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetElementById(@PathParam("elementID") int elementID) {
        try{
            if (elementID==0) return Response.status(600).build();
            Element element = this.elementDAO.getElementById(elementID);
            return Response.status(200).entity(element).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    // Servei per obtenir un element a partir del seu nom
    @GET                                                                            //Servicio para obtener un elemento a partir de su nombre
    @ApiOperation(value = "get an Element by its name", notes = "Get all data 1 user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Element.class),
            @ApiResponse(code = 503, message = "not working well..."),
            @ApiResponse(code = 600, message = "Need to fill in name field.")

    })
    @Path("/Element/getByNAME/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetElementByName(@PathParam("name") String name) {
        try{
            if (name==null) return Response.status(600).build();
            Element element = this.elementDAO.getElementByName(name);
            return Response.status(200).entity(element).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    //Servei per registrar una nova comanda
    @POST                                                                   //Servicio para registrar nuevo Pedido
    @ApiOperation(value = "Register a new Order", notes = "Register an order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Order registered"),
            @ApiResponse(code = 600, message = "Need to fill in date field"),
            @ApiResponse(code = 601, message = "Need to fill in time field"),
            @ApiResponse(code = 602, message = "Need to fill in price field"),

    })
    @Path("/Order/register")
    public Response orderRegister(OrderCredentials orderCredentials) {

        if (orderCredentials.getDate()==null) return Response.status(600).build();
        if (orderCredentials.getTime()==null) return Response.status(601).build();
        if (orderCredentials.getPrice()==0) return Response.status(602).build();

        this.orderDAO.registerOrder(orderCredentials);
        return Response.status(201).build();

    }
    // Servei per registrar un element nou
    @POST                                                                   // Servicio para registrar nuevo elemento en la tienda
    @ApiOperation(value = "Register a new Element", notes = "Register an order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! Element registered"),
            @ApiResponse(code = 600, message = "Need to fill in name field"),
            @ApiResponse(code = 601, message = "Need to fill in description field"),
            @ApiResponse(code = 602, message = "Need to fill in price field"),

    })
    @Path("/Element/register")
    public Response elementRegister(ElementCredentials elementCredentials) {

        if (elementCredentials.getName()==null) return Response.status(600).build();
        if (elementCredentials.getDescription()==null) return Response.status(601).build();
           if (elementCredentials.getPrice()==0) return Response.status(602).build();

        this.elementDAO.registerElement(elementCredentials);
        return Response.status(201).build();

    }
    /******************************************************************************************************************/


}