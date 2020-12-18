package edu.upc.dsa.services;


import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/users")
@Path("/users")
public class UsersService {

    private UserManager tm;

    public UsersService() {

        this.tm = UserManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addUser("Meri", "Meri123");
            this.tm.addUser("Pedro", "Pedro123");
            this.tm.addUser("Montse", "Montse123");
            this.tm.addUser("Antonio", "Antonio123");
        }
    }

    @GET
    @ApiOperation(value = "Get all Users", notes = "Get all users from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.tm.findAll();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build()  ;

    }


    @GET
    @ApiOperation(value = "Get a User", notes = "Get a user from DNI")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") String id) {
        User u = this.tm.getUser(id);
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(u).build();
    }

    @DELETE
    @ApiOperation(value = "Delete a User", notes = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found and deleted"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") String id) {
        User t = this.tm.getUser(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteUser(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Update a User", notes = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/")
    public Response updateUser(User user) {

        User t = this.tm.updateUser(user);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "Register a new User", notes = "Register a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! User registered"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field"),
            @ApiResponse(code = 250, message = "User already exists")

    })
    @Path("/register/{username}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(@PathParam("username") String username, @PathParam("password") String password) {

        if (username==null) return Response.status(600).build();
        if (password==null) return Response.status(601).build();

        if (this.tm.userExists(username)) return Response.status(250).build();

        User us = this.tm.addUser(username, password);
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "A user tries to login", notes = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Login Successful!"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 603, message = "Incorrect password"),
            @ApiResponse(code = 250, message = "User not exists")

    })
    @Path("/login/{username}/{password}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogin(@PathParam("username") String username, @PathParam("password") String password) {

        if (username==null) return Response.status(601).build();
        if (password==null) return Response.status(602).build();
        if (!this.tm.userExists(username)) return Response.status(250).build();
        if (!this.tm.checkPassword(username, password)) return Response.status(603).build();

        return Response.status(201).build();
    }

}