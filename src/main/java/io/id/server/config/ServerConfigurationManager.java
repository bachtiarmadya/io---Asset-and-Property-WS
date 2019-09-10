package io.id.server.config;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import javax.inject.Singleton;
import io.id.app.util.configuration.BaseConfigurationManager;
import io.id.app.util.log.BaseLogger;

@Singleton
public class ServerConfigurationManager extends BaseConfigurationManager {

    private static ServerConfigurationManager instance;

    private ServerConfigurationManager(String... filename) {
        log = new BaseLogger(ServerConfigurationManager.class);
        try {
            prop = new Properties();

            if (filename != null && filename.length == 1) {
                prop.load(new FileReader(new File(filename[0])));
            } else {
                // Use Default File
                prop.load(ServerConfigurationManager.class.getClassLoader()
                        .getResourceAsStream("server.default.properties"));
            }

            prop.putAll(System.getenv());

        } catch (Exception ex) {
            log.error("ServerConfigurationManager", "Error Loading Properties File", ex);
        }
    }

    public static ServerConfigurationManager getInstance(String... filename) {
        if (instance == null) {
            instance = new ServerConfigurationManager(filename);
        }
        return instance;
    }
}
