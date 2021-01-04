package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.Credentials.LoginCredentials;
import edu.upc.dsa.orm.models.Credentials.RegisterCredentials;
import edu.upc.dsa.orm.models.User;
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


@Api(value = "/user")
@Path("user")
public class UserService {

    private final UserDAO userDAO;

    public UserService() {

        this.userDAO = UserDAOImpl.getInstance();

    }

    @GET
    @ApiOperation(value = "Get all Users", notes = "Get all users from BBDD")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("AllUsers/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        List<User> users = this.userDAO.findAll();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

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
    public Response userRegister(RegisterCredentials registerCredentials) {

        if (registerCredentials.getUsername()==null) return Response.status(600).build();
        if (registerCredentials.getPassword()==null) return Response.status(601).build();

        if (this.userDAO.userExists(registerCredentials.getUsername())) return Response.status(250).build();

        if (registerCredentials.getUsername().length() < this.userDAO.getUsername_min_length() || registerCredentials.getUsername().length() > this.userDAO.getUsername_max_length()) return Response.status(604).build();
        if (registerCredentials.getPassword().length() < this.userDAO.getPassword_min_length() || registerCredentials.getPassword().length() > this.userDAO.getPassword_max_length()) return Response.status(605).build();
        if (registerCredentials.getEmail().length() < this.userDAO.getEmail_min_length() || registerCredentials.getEmail().length() > this.userDAO.getEmail_max_length()) return Response.status(606).build();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {

            Date d = sdf.parse(registerCredentials.getBirthdate());
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            int date = c.get(Calendar.DATE);
            LocalDate l1 = LocalDate.of(year, month, date);
            LocalDate now1 = LocalDate.now();
            Period diff1 = Period.between(l1, now1);

            if (diff1.getYears() < this.userDAO.getMin_age()) return Response.status(607).build();

        } catch (Exception e) {


        }

        this.userDAO.registerUser(registerCredentials);
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
        if (!this.userDAO.userExists(loginCredentials.getUsername())) return Response.status(250).build();
        if (!this.userDAO.loginUser(loginCredentials)) return Response.status(603).build();

        return Response.status(201).build();
    }


    @GET
    @ApiOperation(value = "get a User", notes = "Get all data 1 user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 503, message = "not working well...")

    })
/*
    @Path("/{userID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetUserById(@PathParam("userID") int userID) {
        try{
            User user = this.userDAO.getUserById(userID);
            return Response.status(200).entity(user).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
    */


    @Path("/{username}")                                        //servicio para encontrar usuario a partir del username
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetUserByUsername(@PathParam("username") String username) {
        try{
            User user = this.userDAO.getUserByUsername(username);
            return Response.status(200).entity(user).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
}