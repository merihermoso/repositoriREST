package edu.upc.dsa.services;


import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.Credentials.LoginCredentials;
import edu.upc.dsa.models.Credentials.RegisterCredentials;
import edu.upc.dsa.models.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Api(value = "/users")
@Path("/users")
public class UsersService {

    private final UserManager tm;
    private final int username_min_length;
    private final int username_max_length;
    private final int password_min_length;
    private final int password_max_length;
    private final int email_min_length;
    private final int email_max_length;
    private final int min_age;

    public UsersService() {

        this.username_min_length = 4;
        this.username_max_length = 20;
        this.password_min_length = 4;
        this.password_max_length = 20;
        this.email_min_length = 4;
        this.email_max_length = 30;
        this.min_age = 14;

        this.tm = UserManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addUser("Meri", "Meri123", "prova@gmail.com", "12/12/1995");
            this.tm.addUser("Pedro", "Pedro123", "prova@gmail.com", "12/12/1995");
            this.tm.addUser("Montse", "Montse123", "prova@gmail.com", "12/12/1995");
            this.tm.addUser("Antonio", "Antonio123", "prova@gmail.com", "12/12/1995");
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
            @ApiResponse(code = 250, message = "User already exists"),
            @ApiResponse(code = 604, message = "Username too short or too long"),
            @ApiResponse(code = 605, message = "Password too short or too long"),
            @ApiResponse(code = 606, message = "Email too short or too long")
    })
    @Path("/register")
    public Response registerUser(RegisterCredentials registerCredentials) {

        if (registerCredentials.getUsername()==null) return Response.status(600).build();
        if (registerCredentials.getPassword()==null) return Response.status(601).build();

        if (this.tm.userExists(registerCredentials.getUsername())) return Response.status(250).build();

        if (registerCredentials.getUsername().length() < this.username_min_length || registerCredentials.getUsername().length() > this.username_max_length) return Response.status(604).build();
        if (registerCredentials.getPassword().length() < this.password_min_length || registerCredentials.getPassword().length() > this.password_max_length) return Response.status(605).build();
        if (registerCredentials.getEmail().length() < this.email_min_length || registerCredentials.getEmail().length() > this.email_max_length) return Response.status(606).build();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date d = sdf.parse(registerCredentials.getBirthdate().toString());
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int date = c.get(Calendar.DATE);
            LocalDate l1 = LocalDate.of(year, month, date);
            LocalDate now1 = LocalDate.now();
            Period diff1 = Period.between(l1, now1);

            if (diff1.getYears() < this.min_age) return Response.status(607).build();

        } catch (Exception e){

        }

        User us = this.tm.addUser(registerCredentials.getUsername(), registerCredentials.getPassword(), registerCredentials.getEmail(), registerCredentials.getBirthdate());
        return Response.status(201).build();

    }

    @POST
    @ApiOperation(value = "A user tries to login", notes = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Login Successful!"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 603, message = "Incorrect password"),
            @ApiResponse(code = 250, message = "User not exists"),

    })
    @Path("/login")
    public Response userLogin(LoginCredentials loginCredentials) {

        if (loginCredentials.getUsername()==null) return Response.status(601).build();
        if (loginCredentials.getPassword()==null) return Response.status(602).build();
        if (!this.tm.userExists(loginCredentials.getUsername())) return Response.status(250).build();
        if (!this.tm.checkPassword(loginCredentials.getUsername(), loginCredentials.getPassword())) return Response.status(603).build();

        return Response.status(201).build();
    }

}