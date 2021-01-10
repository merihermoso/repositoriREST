package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.Credentials.*;
import edu.upc.dsa.orm.models.Credentials.UserIdResponse;
import edu.upc.dsa.orm.models.UserCredentialsParameters;
import edu.upc.dsa.orm.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Api(value = "/user")
@Path("user")
public class UserService {

    private final UserDAO userDAO;

    public UserService() {

        this.userDAO = UserDAOImpl.getInstance();

    }

    @POST
    @ApiOperation(value = "Register a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful! User registered"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field"),
            @ApiResponse(code = 250, message = "User already exists"),
            @ApiResponse(code = 604, message = "Username too short or too long"),
            @ApiResponse(code = 605, message = "Password too short or too long"),
            @ApiResponse(code = 606, message = "Email too short or too long"),
            @ApiResponse(code = 607, message = "Too young to play")
    })
    @Path("/register")
    public Response register(RegisterCredentials registerCredentials) {

        if (registerCredentials.getUsername() == null) return Response.status(600).build();
        if (registerCredentials.getPassword() == null) return Response.status(601).build();

        if (this.userDAO.userExists(registerCredentials.getUsername())) return Response.status(250).build();

        if (registerCredentials.getUsername().length() < this.userDAO.getUsername_min_length() || registerCredentials.getUsername().length() > this.userDAO.getUsername_max_length()) return Response.status(604).build();
        if (registerCredentials.getPassword().length() < this.userDAO.getPassword_min_length() || registerCredentials.getPassword().length() > this.userDAO.getPassword_max_length()) return Response.status(605).build();
        if (registerCredentials.getEmail().length() < this.userDAO.getEmail_min_length() || registerCredentials.getEmail().length() > this.userDAO.getEmail_max_length()) return Response.status(606).build();

        LocalDate l1 = LocalDate.of(registerCredentials.getBirthdate_year(), registerCredentials.getBirthdate_month(),  registerCredentials.getBirthdate_day());
        Period diff1 = Period.between(l1, LocalDate.now());

        if (diff1.getYears() < this.userDAO.getMin_age()) return Response.status(607).build();

        this.userDAO.registerUser(registerCredentials);
        return Response.status(201).build();

    }

    @POST
    @ApiOperation(value = "Log in with a given username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Login Successful!"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 603, message = "Incorrect password"),
            @ApiResponse(code = 250, message = "User not exists"),

    })
    @Path("/login")
    public Response login(LoginCredentials loginCredentials) {

        if (loginCredentials.getUsername()==null) return Response.status(601).build();
        if (loginCredentials.getPassword()==null) return Response.status(602).build();
        if (!this.userDAO.userExists(loginCredentials.getUsername())) return Response.status(250).build();
        if (!this.userDAO.loginUser(loginCredentials)) return Response.status(603).build();

        return Response.status(201).build();
    }


    @POST
    @ApiOperation(value = "Change user password")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "User not exists"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 604, message = "Need to fill in new password field"),
            @ApiResponse(code = 603, message = "Incorrect password"),
    })
    @Path("/changePassword")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(ChangePasswordCredentials changePasswordCredentials) throws SQLException {

        if (changePasswordCredentials.getUsername() == null) return Response.status(601).build();
        if (changePasswordCredentials.getPassword() == null) return Response.status(602).build();
        if (changePasswordCredentials.getNewPassword() == null) return Response.status(604).build();

        if (this.userDAO.userExists(changePasswordCredentials.getUsername())) {

            LoginCredentials loginCredentials = new LoginCredentials();
            loginCredentials.setUsername(changePasswordCredentials.getUsername());
            loginCredentials.setPassword(changePasswordCredentials.getPassword());

            if (this.userDAO.loginUser(loginCredentials)) {

                this.userDAO.changeUserPassword(changePasswordCredentials);
                return Response.status(201).build();

            } else {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }

    @POST
    @ApiOperation(value = "Change user email")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect password"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 604, message = "Need to fill in new email field"),
            @ApiResponse(code = 404, message = "User not exists"),
    })
    @Path("/changeEmail")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeEmail(ChangeEmailCredentials changeEmailCredentials) throws SQLException {

        if (changeEmailCredentials.getUsername() == null) return Response.status(601).build();
        if (changeEmailCredentials.getPassword() == null) return Response.status(602).build();
        if (changeEmailCredentials.getNewEmail() == null) return Response.status(604).build();

        if (this.userDAO.userExists(changeEmailCredentials.getUsername())) {

            LoginCredentials loginCredentials = new LoginCredentials();
            loginCredentials.setUsername(changeEmailCredentials.getUsername());
            loginCredentials.setPassword(changeEmailCredentials.getPassword());

            if (this.userDAO.loginUser(loginCredentials)) {

                this.userDAO.changeUserEmail(changeEmailCredentials);
                return Response.status(201).build();

            } else {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get all Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<User> users = this.userDAO.findAll();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }

    @POST
    @ApiOperation(value = "Check if a user exists")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User exists"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 404, message = "User not exists"),
    })
    @Path("/userExists")
    public Response userExists(GetUserCredentials getUserCredentials) {

        if (getUserCredentials.getUsername() == null) return Response.status(601).build();

        if (this.userDAO.userExists(getUserCredentials.getUsername())) {

            return Response.status(201).build();

        } else {

            return Response.status(404).build();

        }

    }

    @GET
    @ApiOperation(value = "Get user credentials parameters")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserCredentialsParameters.class),
    })
    @Path("/getUserCredentialsParameters")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCredentialsParameters() {

        UserCredentialsParameters userCredentialsParameters = new UserCredentialsParameters(
                this.userDAO.getUsername_min_length(),
                this.userDAO.getUsername_max_length(),
                this.userDAO.getPassword_min_length(),
                this.userDAO.getPassword_max_length(),
                this.userDAO.getEmail_min_length(),
                this.userDAO.getEmail_max_length(),
                this.userDAO.getMin_age());

        return Response.status(201).entity(userCredentialsParameters).build();

    }


    @POST
    @ApiOperation(value = "Get a User given a username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "User not exists"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("/getUserByUsername")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserByUsername(GetUserCredentials getUserCredentials) {

        if (getUserCredentials.getUsername() == null) return Response.status(601).build();

        if (this.userDAO.userExists(getUserCredentials.getUsername())) {

            try {

                User user = this.userDAO.getUserByUsername(getUserCredentials.getUsername());
                return Response.status(200).entity(user).build();

            } catch (Exception e) {

                return Response.status(503).build();
            }

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get a User given an id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = User.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/getUserById/{userID}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserById(@PathParam("userID") int id) {
        try{

            User user = this.userDAO.getUserById(id);
            return Response.status(200).entity(user).build();

        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }


    @POST
    @ApiOperation(value = "Get a user ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserIdResponse.class),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/getIdByUserame")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdByUsername(GetUserCredentials getUserCredentials) throws SQLException {

        if (getUserCredentials.getUsername() == null) return Response.status(601).build();

        if (this.userDAO.userExists(getUserCredentials.getUsername())) {

            UserIdResponse userIdResponse = new UserIdResponse(this.userDAO.getUserIdByUsername(getUserCredentials.getUsername()));

            return Response.status(201).entity(userIdResponse).build();

        } else {

            return Response.status(404).build();

        }

    }


    /*************************************************** POR HACER... *****************************************/
/*
        NO HARIA EL DELETE... CAMBIARIA EL STATUS-> DELETED

        HAY QUE HACER IFs status...     (tener en cuenta el status del admin...)
        hay que hacer if( username = Admin, password= Admin) -> para que vaya a AdmingSettings.html





 //servicio para encontrar usuario a partir del username
    @Path("/delete/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response deleteUserByUsername(@PathParam("username") String username) {
        try{
            User user = this.userDAO.deleteUserByUsername(username);
            return Response.status(200).entity(user).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
*/
    /**********************************************************************************************************/

}