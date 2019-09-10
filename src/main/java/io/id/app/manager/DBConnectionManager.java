package io.id.app.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Singleton;
import io.id.app.configuration.ApplicationConfigurationManager;
import io.id.app.support.registry.HealthRegistry;
import io.id.app.support.registry.MetricsRegistry;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Singleton
public class DBConnectionManager extends BaseManager {

    private static DBConnectionManager instance;

    private static final String CONFIG_FILTER = "^dataSource.*";
    private static final String PREFIX = "dataSource.";
    private static final String DEFAULT = ".default";
    private static final String CLASS_NAME = ".className";
    private static final String HOST = ".host";
    private static final String PORT = ".port";
    private static final String USERNAME = ".username";
    private static final String PASS = ".password";
    private static final String SCHEMA = ".schema";

    private Map<String, HikariDataSource> dataSourceMap;
    private String defaultDataSource;

    public DBConnectionManager() {
        log = getLogger(DBConnectionManager.class);
        dataSourceMap = new HashMap<>();
        Map<String, String> configMap =
                ApplicationConfigurationManager.getInstance().getConfigurationsByName(CONFIG_FILTER);

        Set<String> poolSet = new HashSet<>();

        configMap.keySet().forEach(key -> {
            String poolName = key.split("\\.")[1];
            poolSet.add(poolName);
        });

        poolSet.forEach(poolName -> {
            try {
                HikariConfig config = new HikariConfig();

                // Set Default DataSource
                if (configMap.containsKey(PREFIX + poolName + DEFAULT)
                        && configMap.get(PREFIX + poolName + DEFAULT).equalsIgnoreCase("true")) {
                    defaultDataSource = poolName;
                }

                config.setPoolName(poolName);
                config.setDataSourceClassName(configMap.get(PREFIX + poolName + CLASS_NAME));
                config.setUsername(configMap.get(PREFIX + poolName + USERNAME));
                config.setPassword(configMap.get(PREFIX + poolName + PASS));

                config.addDataSourceProperty("databaseName", configMap.get(PREFIX + poolName + SCHEMA));
                config.addDataSourceProperty("portNumber", Integer.parseInt(configMap.get(PREFIX + poolName + PORT)));
                config.addDataSourceProperty("serverName", configMap.get(PREFIX + poolName + HOST));

                config.setHealthCheckRegistry(HealthRegistry.getRegistry());
                config.setMetricRegistry(MetricsRegistry.getRegistry());

                HikariDataSource ds = new HikariDataSource(config);

                // Test Connectivity before storing to DataSource Map
                ds.getConnection().close();

                dataSourceMap.put(poolName, ds);

            } catch (Exception ex) {
                log.error("DBConnectionManager", ex);
            }
        });
    }

    public Connection getDefaultConnection() throws SQLException {
        return getConnection(defaultDataSource);
    }

    public Connection getConnection(String name) throws SQLException {
        if (dataSourceMap.containsKey(name)) {
            return dataSourceMap.get(name).getConnection();
        }
        return null;
    }

    public void shutdown() {
        log.debug("shutdown", "Stopping Data Sources");
        dataSourceMap.forEach((key, value) -> value.close());
        log.debug("shutdown", "Data Sources Stopped");
    }

    public static DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        return instance;
    }
}
