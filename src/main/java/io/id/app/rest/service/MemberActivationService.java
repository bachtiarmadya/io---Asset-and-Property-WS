/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.rest.service;

import io.id.app.configuration.ApplicationConfiguration;
import io.id.app.controller.EmailController;
import io.id.app.controller.MemberActivationController;
import io.id.app.controller.OTPController;
import io.id.app.rest.model.MemberActivationModel;
import io.id.app.rest.model.MemberResponse;
import io.id.app.rest.model.MemberValidationModel;
import io.id.app.rest.model.ServerResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author permadi
 */
@Path("member")
@Produces(MediaType.APPLICATION_JSON)
public class MemberActivationService extends BaseService {

    private MemberActivationController memberController;
    private EmailController emailController;
    private OTPController otpController;

    public MemberActivationService() {
        log = getLogger(this.getClass());
        this.memberController = new MemberActivationController();
        this.emailController = new EmailController();
        this.otpController = new OTPController();
    }

    @POST
    @Path("/activation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response memberActivation(MemberActivationModel input) {
        Response res;

        boolean isValid = memberController.checkMember(input.getEmail());
        if (isValid) {
            String otp = otpController.generateOTP(4);
            boolean isSaved = otpController.saveOtp(input.getEmail(), otp);
            if (isSaved) {
                String subject = otpController.getSubject();
                String body = otpController.getBody(input.getEmail(), otp);
                boolean isSend = emailController.sendEmail(input.getEmail(), subject, body);
                if (isSend) {
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
            ServerResponse serverResponse = new ServerResponse(Response.Status.NOT_FOUND, "Account does not exist");
            res = Response.status(Response.Status.NOT_FOUND).entity(serverResponse).build();
        }
        return res;
    }

    @POST
    @Path("/validation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response memberValidation(MemberValidationModel input) {
        Response res;
        boolean isValid = otpController.validateOtp(input.getCode());
        if (isValid) {
            int expiry = Integer.parseInt(getConfig(ApplicationConfiguration.OTP_EXPIRY));
            boolean isExpiry = otpController.validateOtp(input.getCode(), expiry);
            if (isExpiry) {
                boolean validate = otpController.validateOtp(input.getEmail(), input.getCode(), expiry);
                if (validate) {
                    String memberId = memberController.getMemberId(input.getEmail());
                    MemberResponse memberResponse = new MemberResponse(Response.Status.OK,"Success",memberId);
                    res = Response.status(Response.Status.OK).entity(memberResponse).build();
                } else {
                    ServerResponse serverResponse = new ServerResponse(Response.Status.INTERNAL_SERVER_ERROR, "Internal server error");
                    res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(serverResponse).build();
                }
            } else {
                ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "OTP Expired");
                res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
            }
        } else {
            ServerResponse serverResponse = new ServerResponse(Response.Status.FORBIDDEN, "Invalid OTP code");
            res = Response.status(Response.Status.FORBIDDEN).entity(serverResponse).build();
        }
        return res;
    }
}
