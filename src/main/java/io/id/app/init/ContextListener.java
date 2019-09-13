package io.id.app.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import io.id.app.configuration.ApplicationConfigurationManager;
import io.id.app.manager.DBConnectionManager;
import io.id.app.util.log.AppLogger;

@WebListener
public class ContextListener implements ServletContextListener {
    private final AppLogger log;


    /**
     * Default constructor.
     */
    public ContextListener() {
        log = new AppLogger(ContextListener.class);
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        final String methodName = "contextInitialized";
        log.info(methodName, "Start");
        try {
            ApplicationConfigurationManager.getInstance();
            DBConnectionManager.getInstance();
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        log.info(methodName, "Completed");
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        final String methodName = "contextDestroyed";
        log.info(methodName, "Start");
        ApplicationConfigurationManager.getInstance().shutdown();
        DBConnectionManager.getInstance().shutdown();
        log.info(methodName, "Completed");
    }

}
