package edu.upc.dsa.orm.services;


import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.API.*;
import edu.upc.dsa.orm.models.User;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/user")
@Path("user")
public class UserService {

    private final UserDAO userDAO;
    private final GameDAO gameDAO;

    public UserService() {

        this.userDAO = UserDAOImpl.getInstance();
        this.gameDAO = GameDAOImpl.getInstance();

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
    public Response register(RegisterCredentials registerCredentials) throws IllegalAccessException {

        if (registerCredentials.getUsername() == null) return Response.status(600).build();
        if (registerCredentials.getPassword() == null) return Response.status(601).build();

        if (this.userDAO.userExists(registerCredentials.getUsername())) return Response.status(250).build();

        UserSettings userSettings = new UserSettings();


        if (registerCredentials.getUsername().length() < userSettings.getUsername_min_length() || registerCredentials.getUsername().length() > userSettings.getUsername_max_length()) return Response.status(604).build();
        if (registerCredentials.getPassword().length() < userSettings.getPassword_min_length() || registerCredentials.getPassword().length() > userSettings.getPassword_max_length()) return Response.status(605).build();
        if (registerCredentials.getEmail().length() < userSettings.getEmail_min_length() || registerCredentials.getEmail().length() > userSettings.getEmail_max_length()) return Response.status(606).build();

        LocalDate l1 = LocalDate.of(registerCredentials.getBirthdate_year(), registerCredentials.getBirthdate_month(),  registerCredentials.getBirthdate_day());
        Period diff1 = Period.between(l1, LocalDate.now());

        if (diff1.getYears() < userSettings.getMin_age()) return Response.status(607).build();

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
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<User> users = this.userDAO.getAllUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "Get all users UserProfile")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserProfile.class, responseContainer="List"),
    })
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersProfile() {

        List<User> users = this.userDAO.getAllUsers();

        List<UserProfile> userProfileResponse = new ArrayList<>();
        for(User user : users) {
            userProfileResponse.add(new UserProfile(user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getScore(),
                    user.getLevel(),
                    this.gameDAO.getUserPositionByUsername(user.getUsername())));
        }
        GenericEntity<List<UserProfile>> entity = new GenericEntity<List<UserProfile>>(userProfileResponse) {};

        return Response.status(201).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get user credentials settings")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserSettings.class),
    })
    @Path("/settings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserSettings() {

        return Response.status(201).entity(new UserSettings()).build();

    }


    @GET
    @ApiOperation(value = "Get a user UserProfile given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserProfile.class),
            @ApiResponse(code = 404, message = "User not exists"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("/{username}/profile")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserProfileByUsername(@PathParam("username") String username) {

        if (username == null) return Response.status(601).build();

        if (this.userDAO.userExists(username)) {

            try {

                User user = this.userDAO.getUserByUsername(username);

                UserProfile userProfile = new UserProfile(user.getUsername(),
                        user.getEmail(),
                        user.getBirthdate(),
                        user.getScore(),
                        user.getLevel(),
                        this.gameDAO.getUserPositionByUsername(user.getUsername()));

                return Response.status(200).entity(userProfile).build();

            } catch (Exception e) {

                return Response.status(503).build();
            }

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get a User given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "User not exists"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 503, message = "not working well...")

    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserByUsername(@PathParam("username") String username) {

        if (username == null) return Response.status(601).build();

        if (this.userDAO.userExists(username)) {

            try {

                User user = this.userDAO.getUserByUsername(username);

                return Response.status(200).entity(user).build();

            } catch (Exception e) {

                return Response.status(503).build();
            }

        } else {

            return Response.status(404).build();

        }

    }

    @GET
    @ApiOperation(value = "Get the users UserRanking with the most score. It returns the top 20 users by default")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserProfile.class, responseContainer = "List"),
    })
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking() throws SQLException {
        List<User> users = this.gameDAO.getUserRanking();
        List<UserProfile> userProfileResponse = new ArrayList<>();
        for(User user : users) {
            userProfileResponse.add(new UserProfile(user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getScore(),
                    user.getLevel(),
                    this.gameDAO.getUserPositionByUsername(user.getUsername())));
        }
        GenericEntity<List<UserProfile>> entity = new GenericEntity<List<UserProfile>>(userProfileResponse) {};
        return Response.status(201).entity(entity).build();
    }


    //Servicio para obtener la posición en el ranking del usuario
    @GET
    @ApiOperation(value = "Get user UserRanking given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = UserRanking.class),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 404, message = "User not exists"),
    })
    @Path("/{username}/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRankingUser(@PathParam("username") String username) throws SQLException {
        if (username == null) return Response.status(601).build();
        if (this.userDAO.userExists(username)) {
            UserRanking userRanking =
                    new UserRanking(this.gameDAO.getUserPositionByUsername(username));
            return Response.status(201).entity(userRanking).build();
        } else {
            return Response.status(404).build();
        }
    }


    @GET
    @ApiOperation(value = "Get a User given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = User.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserById(@PathParam("id") int id) {

        try{

            User user = this.userDAO.getUserById(id);

            return Response.status(200).entity(user).build();

        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }


    @GET
    @ApiOperation(value = "Get a user UserProfile given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = UserProfile.class),
            @ApiResponse(code = 503, message = "not working well...")
    })
    @Path("/id/{id}/profile")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserProfileById(@PathParam("id") int id) {

        try{

            User user = this.userDAO.getUserById(id);
            UserProfile userProfile = new UserProfile(user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getScore(),
                    user.getLevel(),
                    this.gameDAO.getUserPositionByUsername(user.getUsername()));

            return Response.status(200).entity(userProfile).build();

        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }



    @GET
    @ApiOperation(value = "Get a user ID")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = IdResponse.class),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/getIdByUsername/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIdByUsername(@PathParam("username") String username) throws SQLException {
        if (username == null) return Response.status(601).build();
        if (this.userDAO.userExists(username)) {
            IdResponse idResponse = new IdResponse(this.userDAO.getUserIdByUsername(username));
            return Response.status(201).entity(idResponse).build();
        } else {
            return Response.status(404).build();
        }
    }

    //Servei per modificar tot l'usuari
    @PUT
    @ApiOperation(value = "Update user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "Exception sql..."),
            @ApiResponse(code = 400, message = "not found")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateUser(User user) {
        try{
            int res = userDAO.updateUser(user);
            if (res==0) {
                return Response.status(200).build();
            }
            else{
                return Response.status(400).build();
            }
        }
        catch (Exception e){

            return Response.status(503).build();
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