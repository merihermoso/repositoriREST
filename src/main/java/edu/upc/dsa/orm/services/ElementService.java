package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.element.ElementDAO;
import edu.upc.dsa.orm.dao.element.ElementDAOImpl;

import edu.upc.dsa.orm.models.Elements;
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
import java.util.*;

@Api(value = "/elements")
@Path("/elements")
public class ElementService {

    private ElementDAO elementDAO;

    public ElementService() {
        this.elementDAO = ElementDAOImpl.getInstance();
    }

    @GET
    @ApiOperation(value = "Get all Objects", notes = "Get all Objects from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Elements.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getElements() {

        List<Elements> elements = this.elementDAO.findAll();

        GenericEntity<List<Elements>> entity = new GenericEntity<List<Elements>>(elements) {};
        return Response.status(201).entity(entity).build();

    }


}



