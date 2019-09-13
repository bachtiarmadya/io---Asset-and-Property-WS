package io.id.app.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.inject.Singleton;
import io.id.app.controller.ConfigurationController;
import io.id.app.util.configuration.BaseConfigurationManager;
import io.id.app.util.configuration.Configuration;
import io.id.app.util.log.BaseLogger;
import io.id.server.config.ServerConfiguration;
import io.id.server.config.ServerConfigurationManager;

@Singleton
public class ApplicationConfigurationManager extends BaseConfigurationManager {

    private static ApplicationConfigurationManager instance;

    private ApplicationConfigurationManager() {
        log = new BaseLogger(ApplicationConfigurationManager.class);
        prop = new Properties();
        List<Configuration> configList = new ArrayList<>();
        ConfigurationController controller = new ConfigurationController();

        String configType = getServerConfig(ServerConfiguration.APP_CONFIG_TYPE);

        if (configType.equals("HTTP")) {
            String url = getServerConfig(ServerConfiguration.APP_CONFIG_HTTP_URL);
            configList = controller.getConfiguration(url);

        } else {
            String url = getServerConfig(ServerConfiguration.APP_CONFIG_DB_URL);
            String username = getServerConfig(ServerConfiguration.APP_CONFIG_DB_USER);
            String password = getServerConfig(ServerConfiguration.APP_CONFIG_DB_PASS);

            configList = controller.getConfiguration(url, username, password);
        }
        configList.forEach(config -> prop.put(config.getKey(), config.getValue()));
    }

    private String getServerConfig(String key) {
        return ServerConfigurationManager.getInstance().getConfiguration(key);
    }

    public static ApplicationConfigurationManager getInstance() {

        if (instance == null) {
            instance = new ApplicationConfigurationManager();
        }
        return instance;
    }
}
