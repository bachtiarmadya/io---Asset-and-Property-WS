package io.id.app.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import io.id.app.util.configuration.Configuration;
import io.id.app.util.http.HTTPClient;
import io.id.app.util.http.model.HTTPResponse;
import io.id.app.util.json.JsonHelper;

public class ConfigurationController extends BaseController {

    public ConfigurationController() {
        log = getLogger(this.getClass());
    }

    public List<Configuration> getConfiguration(String url) {
        HTTPResponse response = HTTPClient.get(url);
        return JsonHelper.fromJsonArray(response.getBody(), Configuration.class);
    }

    public List<Configuration> getConfiguration(String url, String username, String password) {
        List<Configuration> configList = new ArrayList<>();
        final String sql = "SELECT * FROM config;";

        try (Connection conn = getConnection(url, username, password);
                PreparedStatement pStmt = conn.prepareStatement(sql);
                ResultSet rs = pStmt.executeQuery();) {

            while (rs.next()) {
                configList.add(new Configuration(rs.getString("key"), rs.getString("value")));
            }

        } catch (Exception ex) {
            log.error("getConfiguration", ex);
        }
        return configList;
    }

    private Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
