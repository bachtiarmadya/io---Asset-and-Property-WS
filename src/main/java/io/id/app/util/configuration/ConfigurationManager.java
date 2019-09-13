package io.id.app.util.configuration;

import java.util.Map;

public interface ConfigurationManager {

    public String getConfiguration(String key);

    public String getConfiguration(String key, String defaultValue);

    public int getIntConfiguration(String key);

    public int getIntConfiguration(String key, int defaultValue);

    public boolean getBoolConfiguration(String key);

    public boolean getBoolConfiguration(String key, boolean defaultValue);

    public Map<String, String> getConfigurationsByName(String filter);

    public void shutdown();
}
