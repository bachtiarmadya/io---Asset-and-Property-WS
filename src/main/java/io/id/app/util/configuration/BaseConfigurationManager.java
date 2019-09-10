package io.id.app.util.configuration;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import io.id.app.util.log.BaseLogger;

public class BaseConfigurationManager implements ConfigurationManager {

    protected BaseLogger log;
    protected Properties prop;

    public BaseConfigurationManager() {
        prop = new Properties();
    }

    public BaseConfigurationManager(Properties prop) {
        this.prop = prop;
    }

    @Override
    public String getConfiguration(String key) {
        return prop.getProperty(key, "");
    }

    @Override
    public int getIntConfiguration(String key) {
        return getIntConfiguration(key, 0);
    }

    @Override
    public int getIntConfiguration(String key, int defaultValue) {
        return Integer.parseInt(getConfiguration(key, String.valueOf(defaultValue)));
    }

    @Override
    public String getConfiguration(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }

    @Override
    public boolean getBoolConfiguration(String key) {
        return getBoolConfiguration(key, false);
    }

    @Override
    public boolean getBoolConfiguration(String key, boolean defaultValue) {
        String config = getConfiguration(key, String.valueOf(defaultValue));
        return Boolean.parseBoolean(config);
    }

    @Override
    public Map<String, String> getConfigurationsByName(String filter) {
        return prop.keySet().stream().filter(key -> ((String) key).matches(filter))
                .collect(Collectors.toMap(key -> (String) key, key -> (String) prop.get(key)));
    }

    @Override
    public void shutdown() {
        prop.clear();
        prop = null;
    }
}
