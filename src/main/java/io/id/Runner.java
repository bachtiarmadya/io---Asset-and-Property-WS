package io.id;

import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ShutdownHandler;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.glassfish.jersey.servlet.ServletContainer;
import io.id.app.init.ContextListener;
import io.id.app.manager.DocServiceManager;
import io.id.app.manager.RestServiceManager;
import io.id.app.util.log.AppLogger;
import io.id.server.config.ServerConfiguration;
import io.id.server.config.ServerConfigurationManager;
import io.id.server.handler.CustomErrorHandler;

public class Runner {

    private static AppLogger log;

    public static void main(final String[] args) {
        log = new AppLogger(Runner.class);
        final String methodName = "main";

        init(args);

        log.debug(methodName, "Initializing Server");

        QueuedThreadPool threadPool = new QueuedThreadPool(getIntConfig(ServerConfiguration.THREADS_MAX),
                getIntConfig(ServerConfiguration.THREADS_MIN));

        Server server = new Server(threadPool);

        try (ServerConnector http = new ServerConnector(server, new HttpConnectionFactory())) {

            // HTTP
            http.setPort(getIntConfig(ServerConfiguration.HTTP_PORT));
            http.setIdleTimeout(getIntConfig(ServerConfiguration.HTTP_IDLE_TIMEOUT));
            server.addConnector(http);

            // Extra options
            server.setDumpAfterStart(false);
            server.setDumpBeforeStop(false);
            server.setStopAtShutdown(true);

          // ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

            // App Context
            context.setContextPath(getConfig(ServerConfiguration.APP_CONTEXT));

            // Application Context Listener
            context.getServletHandler().addListener(new ListenerHolder(ContextListener.class));

            // Servlet
            log.debug(methodName, "Initializing Servlets");
            setServiceServlet(context);
            setSwaggerServlet(context);

            log.debug(methodName, "Initializing Error Handler");
            context.setErrorHandler(new CustomErrorHandler());

            log.debug(methodName, "Initializing Shutdown Handler");
            ShutdownHandler shutdownHandler = new ShutdownHandler("password", true, true);
            server.setHandler(new HandlerCollection(context, shutdownHandler));

            log.debug(methodName, "Starting Server");
            server.start();
            server.join();
            server.dumpStdErr();
            log.debug(methodName, "Server Started");

        } catch (Exception ex) {
            log.error("main", ex);
        }
    }

    private static void init(String... args) {
        final String methodName = "init";
        log.info(methodName, "Start");
        ServerConfigurationManager.getInstance(args);
        log.info(methodName, "Completed");
    }

    private static int getIntConfig(String key) {
        return ServerConfigurationManager.getInstance().getIntConfiguration(key);
    }

    private static String getConfig(String key) {
        return ServerConfigurationManager.getInstance().getConfiguration(key);
    }

    private static void setServiceServlet(ServletContextHandler context) {
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(new RestServiceManager()));
        servletHolder.setInitOrder(1);
        context.addServlet(servletHolder, getConfig(ServerConfiguration.APP_SERVICE_PATH));
    }

    private static void setSwaggerServlet(ServletContextHandler context) {
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(new DocServiceManager()));
        servletHolder.setInitOrder(2);
        context.addServlet(servletHolder, getConfig(ServerConfiguration.APP_DOCS_PATH));
    }
}
