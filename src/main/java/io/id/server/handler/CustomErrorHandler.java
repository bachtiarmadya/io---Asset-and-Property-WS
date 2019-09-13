package io.id.server.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import io.id.app.util.json.JsonHelper;
import io.id.app.util.rest.model.ServiceResponse;

public class CustomErrorHandler extends ErrorPageErrorHandler {

    private final String errorMessage;

    public CustomErrorHandler() {
        errorMessage = JsonHelper.toJson(new ServiceResponse(Status.NOT_FOUND, "Not Found"));
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        response.setStatus(Status.NOT_FOUND.getStatusCode());
        response.getWriter().append(errorMessage);
    }
}
