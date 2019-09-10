/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import io.id.app.configuration.ApplicationConfiguration;
import io.id.app.controller.EmailController;
import io.id.app.controller.PasswordController;
import io.id.app.controller.TokenController;
import io.id.app.controller.AdminController;
import io.id.app.controller.UserController;
import io.id.app.rest.model.ChangePasswordRequest;
import io.id.app.rest.model.ResetPasswordRequest;
import io.id.app.rest.model.ServerResponse;
import io.id.app.rest.model.UserRequestModel;
import io.id.app.rest.model.ValidateModel;
import io.id.app.rest.model.ValidateTokenModel;
import io.id.app.validator.PasswordValidator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("password")
@Produces(MediaType.APPLICATION_JSON)
public class PasswordService extends BaseService {

    @Context
    HttpServletRequest request;

    private TokenController tokenController;
    private PasswordController passwordController;
    private EmailController emailController;
    private UserController userController;
    private PasswordValidator passwordValidator;

    public PasswordService() {
        log = getLogger(this.getClass());
        this.tokenController = new TokenController();
        this.passwordController = new PasswordController();
        this.emailController = new EmailController();
        this.userController = new UserController();
        this.passwordValidator = new PasswordValidator();
    }

    @POST
    @Path("/reset/account")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response triggerEmail(ValidateModel input) {
        Response res;
        boolean isExist = userController.checkEmail(input.getEmail());
        if (isExist) {
            String token = tokenController.generateToken(input.getEmail());
            boolean isGenerate = tokenController.save(input.getEmail(), token);
            if (isGenerate) {
                String subject = emailController.getSubject();
                String body = emailController.getBody(input.getEmail(), token);
                boolean isSend = emailController.sendEmail(input.getEmail(), subject, body);
                if (isSend) {
                    HttpSession session = request.getSession();
                    session.setAttribute("email", input.getEmail());
                    session.setAttribute("token", token);

                    ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
                    res = Response.status(Response.Status.OK).entity(serverResponse).build();
                } else {
                    ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
                    res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
                }
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Email not found");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }
        return res;
    }

    @POST
    @Path("/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validateToken(ValidateTokenModel input) {
        Response res;
        boolean isExist = tokenController.validateToken(input.getToken());
        if (isExist) {
            int expiry = Integer.parseInt(getConfig(ApplicationConfiguration.TOKEN_EXPIRY));
            boolean isExpired = tokenController.validateToken(input.getToken(), expiry);
            if (isExpired) {
                ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
                res = Response.status(Response.Status.OK).entity(serverResponse).build();
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Token expired");
                res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Invalid token");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }

    @POST
    @Path("/reset")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resetPassword(ResetPasswordRequest input) {
        Response res;
        HttpSession session = request.getSession(false);

        if (session != null) {
            String savedToken = session.getAttribute("token").toString();
            if (savedToken.equals(input.getToken())) {
                String email = session.getAttribute("email").toString();
                String username = userController.checkUsername(email);
                boolean isExist = tokenController.validateToken(input.getToken());
                if (isExist) {
                    int expiry = Integer.parseInt(getConfig(ApplicationConfiguration.TOKEN_EXPIRY));
                    boolean isExpired = tokenController.validateToken(input.getToken(), expiry);
                    if (isExpired) {
                        boolean isValid = passwordValidator.validate(input.getPassword());
                        if (isValid) {
                            boolean isReset = passwordController.resetPassword(username, input.getPassword());
                            if (isReset) {
                                ServerResponse serverResponse = new ServerResponse(Response.Status.OK, "Success");
                                res = Response.status(Response.Status.OK).entity(serverResponse).build();
                            } else {
                                ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
                                res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
                            }
                        } else {
                            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Password policy failed");
                            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
                        }
                    } else {
                        ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Token expired");
                        res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
                    }
                } else {
                    ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Invalid token");
                    res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
                }
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Invalid token");
                res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Forbidden");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }

        return res;
    }

}
