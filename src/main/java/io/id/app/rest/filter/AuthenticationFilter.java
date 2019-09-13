package io.id.app.rest.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;
import io.id.app.util.rest.model.ServiceResponse;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Context
    private HttpServletRequest httpServletRequest;

    private final ResponseBuilder accessDenied;
    private final ResponseBuilder forbidden;

    public AuthenticationFilter() {
        accessDenied =
                Response.status(Response.Status.UNAUTHORIZED).entity(new ServiceResponse(Response.Status.UNAUTHORIZED));
        forbidden = Response.status(Response.Status.FORBIDDEN).entity(new ServiceResponse(Response.Status.FORBIDDEN));
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (!hasSecurityAnnotations(resourceInfo)) {
            return;
        }

        // Session is Present
        if (hasSession(httpServletRequest)) {

            List<String> roleList = getMethodRoles(resourceInfo);

            if (!hasRoles(httpServletRequest, roleList)) {
                requestContext.abortWith(forbidden.build());
            }
        } else {
            requestContext.abortWith(accessDenied.build());
        }
    }

    private boolean hasSession(HttpServletRequest request) {
        return request.getSession() != null;
    }

    private boolean hasRoles(HttpServletRequest request, List<String> roleList) {
        final HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("");

        for (String allowedRole : roleList) {
            if (role.equals(allowedRole)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getMethodRoles(ResourceInfo resourceInfo) {
        Method method = resourceInfo.getResourceMethod();
        Class<?> clazz = resourceInfo.getResourceClass();

        List<String> roleList = new ArrayList<>();

        if (method.isAnnotationPresent(RolesAllowed.class)) {
            roleList = Arrays.asList(method.getAnnotation(RolesAllowed.class).value());
        } else if (clazz.isAnnotationPresent(RolesAllowed.class)) {
            roleList = Arrays.asList(clazz.getAnnotation(RolesAllowed.class).value());
        }

        return roleList;
    }

    private boolean hasSecurityAnnotations(ResourceInfo resourceInfo) {
        Method method = resourceInfo.getResourceMethod();
        Class<?> clazz = resourceInfo.getResourceClass();
        return method.isAnnotationPresent(PermitAll.class) || method.isAnnotationPresent(RolesAllowed.class)
                || clazz.isAnnotationPresent(PermitAll.class) || clazz.isAnnotationPresent(RolesAllowed.class);

    }

}
