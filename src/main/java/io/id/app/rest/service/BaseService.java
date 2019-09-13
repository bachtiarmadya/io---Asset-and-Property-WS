package io.id.app.rest.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import io.id.app.configuration.ApplicationConfigurationManager;
import io.id.app.util.log.AppLogger;
import io.id.app.util.rest.exception.RESTException;
import io.id.app.util.rest.model.ServiceResponse;

public class BaseService {

    protected AppLogger log;

    protected AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    protected boolean validateIntParam(int param) {
        return param > 0;
    }

    protected boolean validateParam(String param) {
        return param != null && !param.trim().isEmpty();
    }

    protected boolean validateParams(String... params) {

        for (String param : params) {
            if (!validateParam(param)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validateParam(String param, String regex) {
        return validateParam(param) && param.matches(regex);
    }

    protected <T> boolean validateParam(List<T> list) {
        return list != null && !list.isEmpty();
    }

    protected Response buildResponse(Status status) {
        return Response.status(status).header("Access-Control-Allow-Origin", "*").entity(new ServiceResponse(status)).build();
    }

    protected Response buildResponse(Status status, String description) {
        return buildResponse(status, new ServiceResponse(status, description));
    }

    protected Response buildResponse(Status status, Object entity) {
        return Response.status(status).header("Access-Control-Allow-Origin", "*").entity(entity).build();
    }

    protected Response buildResponse(RESTException ex) {
        return Response.status(ex.getStatus()).entity(ex).type(MediaType.APPLICATION_JSON).build();
    }

    protected ExecutorService getThreadPool() {
        return Executors.newFixedThreadPool(10);
    }

    protected void start(String methodName) {
        log.debug(methodName, "Start");
    }

    protected void completed(String methodName) {
        log.debug(methodName, "Completed");
    }

    protected boolean validateParam(String... params) {
        for (String param : params) {

            if (param == null || param.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    protected String getConfig(String key) {
        return ApplicationConfigurationManager.getInstance().getConfiguration(key);
    }
}
