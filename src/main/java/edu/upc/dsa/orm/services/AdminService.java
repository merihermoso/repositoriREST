package edu.upc.dsa.orm.services;

import edu.upc.dsa.orm.dao.player.PlayerDAO;
import edu.upc.dsa.orm.dao.player.PlayerDAOImpl;
import edu.upc.dsa.orm.dao.user.UserDAO;
import edu.upc.dsa.orm.dao.user.UserDAOImpl;
import io.swagger.annotations.Api;

import javax.ws.rs.*;

@Api(value = "/admin")
@Path("admin")

public class AdminService {
    private final UserDAO userDAO;
    private final PlayerDAO playerDAO;

    public AdminService() {
        this.userDAO = UserDAOImpl.getInstance();
        this.playerDAO = PlayerDAOImpl.getInstance();

    }
/**********************************************     modificaciones USER      *******************************************/
/*

    @POST
    @ApiOperation(value = "Change user Status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect status"),
    })

    @Path("/changeStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserStatus(ChangeStatus changeStatusCredentials) throws SQLException {
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(changeStatusCredentials.getUsername());
        loginCredentials.setPassword(changeStatusCredentials.getPassword());
        if (this.userDAO.loginUser(loginCredentials)) {         //fer if user status = Admin (que pugui fer tot)
            this.userDAO.changeUserStatus(changeStatusCredentials);
            return Response.status(200).build();
        } else {
            return Response.status(603).build();
        }
    }

    @POST
    @ApiOperation(value = "Change user Level")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect status"),
    })
    @Path("/changeLevel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserLevel(ChangeLevel changeLevelCredentials) throws SQLException {

        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(changeLevelCredentials.getUsername());
        loginCredentials.setPassword(changeLevelCredentials.getPassword());

        if (this.userDAO.loginUser(loginCredentials)) {         //fer if user status = Admin (que pugui fer tot)
            this.userDAO.changeUserLevel(changeLevelCredentials);
            return Response.status(200).build();

        } else {

            return Response.status(603).build();

        }

    }
    @POST
    @ApiOperation(value = "Change user Score")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect score"),
    })
    @Path("/changeScore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserScore(ChangeScore changeScoreCredentials) throws SQLException {
        LoginCredentials loginCredentials = new LoginCredentials();
        loginCredentials.setUsername(changeScoreCredentials.getUsername());
        loginCredentials.setPassword(changeScoreCredentials.getPassword());

        if (this.userDAO.loginUser(loginCredentials)) {         //fer if user status = Admin (que pugui fer tot)
            this.userDAO.changeUserScore(changeScoreCredentials);
            return Response.status(200).build();

        } else {
            return Response.status(603).build();

        }

    }
/****************************************** modificacions player ******************************************************/
    /*@POST
    @ApiOperation(value = "Change player Score")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),

    })
    @Path("/changePlayerScore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePlayerScore(ChangePlayerScore changePlayerScore) throws SQLException {
          //fer if user status = Admin (que pugui fer tot)
            this.playerDAO.changePlayerScore(changePlayerScore);
            return Response.status(200).build();


    }
    @POST
    @ApiOperation(value = "Change player Level")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect score"),
    })
    @Path("/changePlayerLevel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePlayerLevel(ChangePlayerScore changePlayerScore) throws SQLException {


        if (this.playerDAO.changePlayerScore(changePlayerScore)) {         //fer if user status = Admin (que pugui fer tot)
            return Response.status(200).build();

        } else {
            return Response.status(603).build();

        }

    }
    @POST
    @ApiOperation(value = "Change player Coins")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect score"),
    })
    @Path("/changePlayerCoins")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePlayerCoins(ChangePlayerCoins changePlayerCoins) throws SQLException {
        LoginCredentials loginCredentials = new LoginCredentials();                                             //CANVIAR!!!!!
        loginCredentials.setUsername(changePlayerCoins.getUsername());

        if (this.userDAO.loginUser(loginCredentials)) {         //fer if user status = Admin (que pugui fer tot)
            this.playerDAO.changePlayerCoins(changePlayerCoins);
            return Response.status(200).build();

        } else {
            return Response.status(603).build();

        }

    }
    @POST
    @ApiOperation(value = "Change player Status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect status"),
    })
    @Path("/changePlayerStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePlayerStatus(ChangePlayerStatus changePlayerStatus) throws SQLException {
        if (this.playerDAO.changePlayerStatus(changePlayerStatus)) {         //fer if user status = Admin (que pugui fer tot)
            return Response.status(200).build();

        } else {
            return Response.status(603).build();

        }

    }
    @POST
    @ApiOperation(value = "Change player Speed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 603, message = "Incorrect score"),
    })
    @Path("/changePlayerSpeed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePlayerSpeed(ChangePlayerSpeed changePlayerSpeed) throws SQLException {
        LoginCredentials loginCredentials = new LoginCredentials();                                             //CANVIAR!!!!!
        loginCredentials.setUsername(changePlayerSpeed.getUsername());

        if (this.userDAO.loginUser(loginCredentials)) {         //fer if user status = Admin (que pugui fer tot)
            this.playerDAO.changePlayerSpeed(changePlayerSpeed);
            return Response.status(200).build();

        } else {
            return Response.status(603).build();

        }

    }
    /************************************** modificaciones TIENDA  ****************************************************/

    /**********************************************************************************************************/

}