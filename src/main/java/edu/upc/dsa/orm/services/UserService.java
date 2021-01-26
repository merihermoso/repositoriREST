package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.game.GameDAO;
import edu.upc.dsa.orm.dao.game.GameDAOImpl;
import edu.upc.dsa.orm.dao.orders.OrdersDAO;
import edu.upc.dsa.orm.dao.orders.OrdersDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import edu.upc.dsa.orm.models.API.*;
import edu.upc.dsa.orm.models.Game;
import edu.upc.dsa.orm.models.Orders;
import edu.upc.dsa.orm.models.User;
import edu.upc.dsa.orm.util.MazeGenerator;
import edu.upc.dsa.orm.util.SendingMailSSL;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@Api(value = "/user")
@Path("user")
public class UserService {

    private final UserDAO userDAO;
    private final GameDAO gameDAO;
    private final OrdersDAO ordersDAO;

    public UserService() {

        userDAO = UserDAOImpl.getInstance();
        gameDAO = GameDAOImpl.getInstance();
        ordersDAO = OrdersDAOImpl.getInstance();

    }

    // CREATE

    @POST
    @ApiOperation(value = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 250, message = "User already exists")
    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {

        if (!userDAO.exists(user.getUsername()) && !userDAO.existsEmail(user.getEmail())) {

            userDAO.create(user);
            return Response.status(201).build();

        } else {

            return Response.status(250).build();
        }

    }


    @POST
    @ApiOperation(value = "Register a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful! User registered"),
            @ApiResponse(code = 600, message = "Need to fill in username field"),
            @ApiResponse(code = 601, message = "Need to fill in password field"),
            @ApiResponse(code = 250, message = "User already exists"),
            @ApiResponse(code = 604, message = "Username too short or too long"),
            @ApiResponse(code = 605, message = "Password too short or too long"),
            @ApiResponse(code = 606, message = "Email too short or too long"),
            @ApiResponse(code = 607, message = "Too young to play"),
            @ApiResponse(code = 608, message = "Email already in use")
    })
    @Path("/register")
    public Response register(RegisterCredentials registerCredentials) {

        if (registerCredentials.getUsername() == null) return Response.status(600).build();
        if (registerCredentials.getPassword() == null) return Response.status(601).build();

        if (userDAO.exists(registerCredentials.getUsername())) return Response.status(250).build();
        if (userDAO.existsEmail(registerCredentials.getEmail())) return Response.status(608).build();

        UserSettings userSettings = userDAO.readSettings();

        if (registerCredentials.getUsername().length() < userSettings.getUsername_min_length()
                || registerCredentials.getUsername().length() > userSettings.getUsername_max_length())
            return Response.status(604).build();

        if (registerCredentials.getPassword().length() < userSettings.getPassword_min_length()
                || registerCredentials.getPassword().length() > userSettings.getPassword_max_length())
            return Response.status(605).build();

        if (registerCredentials.getEmail().length() < userSettings.getEmail_min_length()
                || registerCredentials.getEmail().length() > userSettings.getEmail_max_length())
            return Response.status(606).build();

        LocalDate l1 = LocalDate.of(registerCredentials.getBirthdate_year(), registerCredentials.getBirthdate_month(),
                registerCredentials.getBirthdate_day());

        Period diff1 = Period.between(l1, LocalDate.now());

        if (diff1.getYears() < userSettings.getMin_age()) return Response.status(607).build();

        userDAO.registerUser(registerCredentials);
        return Response.status(200).build();

    }

    // READ

    @POST
    @ApiOperation(value = "Log in with a given username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login Successful!"),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 602, message = "Need to fill in password field"),
            @ApiResponse(code = 603, message = "Incorrect password"),
            @ApiResponse(code = 404, message = "User not found"),

    })
    @Path("/login")
    public Response login(LoginCredentials loginCredentials) {

        if (loginCredentials.getUsername() == null) return Response.status(601).build();
        if (loginCredentials.getPassword() == null) return Response.status(602).build();
        if (!userDAO.exists(loginCredentials.getUsername())) return Response.status(404).build();

        if (!userDAO.checkPassword(loginCredentials.getUsername()
                , loginCredentials.getPassword())) return Response.status(603).build();

        return Response.status(200).build();
    }


    @GET
    @ApiOperation(value = "Get all Users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        List<User> users = userDAO.readAll();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(entity).build();

    }

    @GET
    @ApiOperation(value = "Get all users UserProfile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = UserProfile.class, responseContainer="List"),
    })
    @Path("/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersProfile() {

        List<User> users = userDAO.readAll();

        List<UserProfile> userProfileResponse = new ArrayList<>();
        for(User user : users) {
            userProfileResponse.add(new UserProfile(
                    user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getProfile_photo(),
                    user.getScore(),
                    userDAO.readRankingPositionByParameter("username", user.getUsername())));
        }
        GenericEntity<List<UserProfile>> entity = new GenericEntity<List<UserProfile>>(userProfileResponse) {};

        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get user credentials settings")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = UserSettings.class),
    })
    @Path("/settings")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserSettings() {

        return Response.status(200).entity(userDAO.readSettings()).build();

    }


    @GET
    @ApiOperation(value = "Get a user UserProfile given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = UserProfile.class),
            @ApiResponse(code = 404, message = "User not exists"),

    })
    @Path("/{username}/profile")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserProfileByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            User user = userDAO.readByParameter("username", username);

            UserProfile userProfile = new UserProfile(user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getProfile_photo(),
                    user.getScore(),
                    userDAO.readRankingPositionByParameter("username", username));

            return Response.status(200).entity(userProfile).build();

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get a User given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 404, message = "User not exists"),

    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            User user = userDAO.readByParameter("username", username);

            return Response.status(200).entity(user).build();

        } else {

            return Response.status(404).build();

        }

    }

    @GET
    @ApiOperation(value = "Get the users UserRanking with the most score. It returns the top 20 users by default")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = UserRanking.class, responseContainer = "List"),
    })
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking() {

        List<UserRanking> userRanking = userDAO.readRanking();

        GenericEntity<List<UserRanking>> entity = new GenericEntity<List<UserRanking>>(userRanking) {};
        return Response.status(200).entity(entity).build();

    }


    @GET
    @ApiOperation(value = "Get user UserRanking given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = UserRanking.class),
            @ApiResponse(code = 404, message = "User not exists"),
    })
    @Path("/{username}/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRankingUserByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            User user = userDAO.readByParameter("username", username);

            UserRanking userRanking =
                    new UserRanking(user.getUsername(), user.getScore(), user.getProfile_photo(),
                            userDAO.readRankingPositionByParameter("username", username));

            return Response.status(200).entity(userRanking).build();

        } else {

            return Response.status(404).build();

        }
    }



    @GET
    @ApiOperation(value = "Get user UserRanking given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = UserRanking.class),
            @ApiResponse(code = 601, message = "Need to fill in username field"),
            @ApiResponse(code = 404, message = "User not exists"),
    })
    @Path("/id/{id}/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRankingUserById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            User user = userDAO.readByParameter("id", id);

            UserRanking userRanking =
                    new UserRanking(user.getUsername(), user.getScore(), user.getProfile_photo(),
                            userDAO.readRankingPositionByParameter("id", id));

            return Response.status(200).entity(userRanking).build();

        } else {

            return Response.status(404).build();

        }
    }


    @GET
    @ApiOperation(value = "Get a User given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response getUserById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            User user = userDAO.readByParameter("id", id);
            return Response.status(200).entity(user).build();

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get a user UserProfile given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesful", response = UserProfile.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/id/{id}/profile")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserProfileById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            User user = userDAO.readByParameter("id", id);

            UserProfile userProfile = new UserProfile(user.getUsername(),
                    user.getEmail(),
                    user.getBirthdate(),
                    user.getProfile_photo(),
                    user.getScore(),
                    userDAO.readRankingPositionByParameter("username", user.getUsername()));

            return Response.status(200).entity(userProfile).build();

        } else {

            return Response.status(404).build();

        }

    }



    @GET
    @ApiOperation(value = "Get a user parameter by its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Parameter not found"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/{username}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readParameterByUsername(@PathParam("username") String username,
                                            @PathParam("parameter") String parameter) {

        if (userDAO.exists(username)) {

            try {

                Field field = User.class.getDeclaredField(parameter);

                Object res = userDAO.readParameterByParameter(parameter, "username", username);
                return Response.status(200).entity(res).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Send email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
    })
    @Path("/sendemail")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sendEmail() {

        MazeGenerator mazeGenerator = new MazeGenerator(400, 400);

        SendingMailSSL sendingMailSSL = new SendingMailSSL();

        return Response.status(200).build();

    }

    @GET
    @ApiOperation(value = "Get a user parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "Not found"),
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response readParameterById(@PathParam("id") int id,
                                             @PathParam("parameter") String parameter) {

        if (userDAO.existsId(id)) {

            Object res = userDAO.readParameterByParameter(parameter, "id", id);
            return Response.status(200).entity(res).build();

        } else {

            return Response.status(404).build();

        }

    }


    @GET
    @ApiOperation(value = "Get a User games list given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/id/{id}/game")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserGamesById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            List<Game> games = gameDAO.readAllByParameter("id_user", id);

            GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
            };
            return Response.status(200).entity(entity).build();

        } else {
            return Response.status(404).build();
        }

    }


    @GET
    @ApiOperation(value = "Get a User games list given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Game.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}/game")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserGamesByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            int id = (int) userDAO.readParameterByParameter("id", "username", username);

            List<Game> games = gameDAO.readAllByParameter("id_user", id);

            GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(games) {
            };
            return Response.status(200).entity(entity).build();

        } else {
            return Response.status(404).build();
        }

    }


    @POST
    @ApiOperation(value = "Create Game for a given username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not exists")
    })
    @Path("/{username}/game")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGameByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            int id = (int) userDAO.readParameterByParameter("id", "username", username);

            gameDAO.generate(id);
            return Response.status(201).build();

        } else {
            return Response.status(404).build();
        }

    }



    @POST
    @ApiOperation(value = "Create Game for a given username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Game.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not exists")
    })
    @Path("/{id}/id/game")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createGameById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            gameDAO.generate(id);
            return Response.status(201).build();

        } else {
            return Response.status(404).build();
        }

    }

    ///

    @GET
    @ApiOperation(value = "Get a User orders list given its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Orders.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/id/{id}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserOrdersById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            List<Orders> ordersList = ordersDAO.readAllByParameter("id_user", id);

            GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(ordersList) {
            };
            return Response.status(200).entity(entity).build();

        } else {
            return Response.status(404).build();
        }

    }


    @GET
    @ApiOperation(value = "Get a User orders list given its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Orders.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserOrdersByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            int id = (int) userDAO.readParameterByParameter("id", "username", username);

            List<Orders> ordersList = ordersDAO.readAllByParameter("id_user", id);

            GenericEntity<List<Orders>> entity = new GenericEntity<List<Orders>>(ordersList) {
            };
            return Response.status(200).entity(entity).build();

        } else {
            return Response.status(404).build();
        }

    }

    //UPDATE

    @PUT
    @ApiOperation(value = "Update a user by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/id/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserById(@PathParam("id") int id, User user) {

        if (userDAO.existsId(id) && id == user.getId()) {

            userDAO.update(user);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }

    }

    @PUT
    @ApiOperation(value = "Update a user by its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserByUsername(@PathParam("username") String username, User user) {

        if (userDAO.exists(username) && username.equals(user.getUsername())) {

            userDAO.updateByParameter(user,"username", username);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();
        }


    }


    @PUT
    @ApiOperation(value = "Update a user parameter by its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 603, message = "Parameter not found"),
            @ApiResponse(code = 604, message = "You must enter a new parameter value")
    })
    @Path("/{username}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParameterByUsername(@PathParam("username") String username,
                                              @PathParam("parameter") String parameter,
                                              UpdateParameterRequest updateParameterRequest) {

        String parameterValue = updateParameterRequest.getParameterValue();

        if (userDAO.exists(username)) {

            if (!parameterValue.equals("")) {

                try {

                    if (User.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                        userDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                                , "username", username);

                    } else {
                        userDAO.updateParameterByParameter(parameter, parameterValue, "username", username);
                    }

                    return Response.status(200).build();

                } catch (NoSuchFieldException noSuchFieldException) {

                    return Response.status(603).build();

                }

            } else {

                return Response.status(604).build();
            }

        } else {

            return Response.status(404).build();

        }

    }


    @PUT
    @ApiOperation(value = "Update a user parameter by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 603, message = "Parameter not found")
    })
    @Path("/id/{id}/{parameter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateParameterById(@PathParam("id") int id,
                                              @PathParam("parameter") String parameter,
                                              UpdateParameterRequest updateParameterRequest) {

        String parameterValue = updateParameterRequest.getParameterValue();

        if (userDAO.existsId(id)) {

            try {

                if (User.class.getDeclaredField(parameter).getType().isAssignableFrom(Integer.class)) {
                    userDAO.updateParameterByParameter(parameter, Integer.parseInt(parameterValue)
                            , "id", id);

                } else {
                    userDAO.updateParameterByParameter(parameter, parameterValue, "id", id);
                }

                return Response.status(200).build();

            } catch (NoSuchFieldException noSuchFieldException) {

                return Response.status(603).build();

            }

        } else {

            return Response.status(404).build();

        }

    }


    @PUT
    @ApiOperation(value = "Update a user password by its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/{username}/password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePasswordByUsername(@PathParam("username") String username,
                                              UpdateParameterRequest updateParameterRequest) {

        String newPassword = updateParameterRequest.getParameterValue();

        if (userDAO.exists(username)) {

            String newPasswordHash = userDAO.getHashString(newPassword, "SHA-256");

            userDAO.updateParameterByParameter("password", newPasswordHash, "username", username);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }


    @PUT
    @ApiOperation(value = "Update a user password by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/id/{id}/password")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePasswordById(@PathParam("id") int id,
                                             UpdateParameterRequest updateParameterRequest) {

        String newPassword = updateParameterRequest.getParameterValue();

        if (userDAO.existsId(id)) {

            String newPasswordHash = userDAO.getHashString(newPassword, "SHA-256");

            userDAO.updateParameterByParameter("password", newPasswordHash, "id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }


    // DELETE

    @DELETE
    @ApiOperation(value = "Delete a user by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") int id) {

        if (userDAO.existsId(id)) {

            userDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }


    @DELETE
    @ApiOperation(value = "Delete a user by its username")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 404, message = "User not found"),
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteByUsername(@PathParam("username") String username) {

        if (userDAO.exists(username)) {

            int id = (int) userDAO.readParameterByParameter("id", "username", username);

            userDAO.deleteByParameter("id", id);
            return Response.status(200).build();

        } else {

            return Response.status(404).build();

        }

    }



}