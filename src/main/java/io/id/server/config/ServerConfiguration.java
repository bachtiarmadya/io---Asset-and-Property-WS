package io.id.server.config;

public class ServerConfiguration {

    private ServerConfiguration() {}

    // SERVER CONFIG
    public static final String HTTP_PORT = "http.port";
    public static final String HTTP_IDLE_TIMEOUT = "http.idle.timeout";

    public static final String HTTPS_PORT = "https.port";
    public static final String HTTPS_IDLE_TIMEOUT = "https.idle.timeout";

    public static final String THREADS_MIN = "threads.min";
    public static final String THREADS_MAX = "threads.max";

    // APP Endpoint Config
    public static final String APP_CONTEXT = "app.context";
    public static final String APP_SERVICE_PATH = "app.service.path";
    public static final String APP_DOCS_PATH = "app.docs.path";

    // APP CONFIG
    public static final String APP_CONFIG_TYPE = "app.config.type";

    public static final String APP_CONFIG_HTTP_URL = "app.config.http.url";
    public static final String APP_CONFIG_HTTP_USER = "app.config.http.user";
    public static final String APP_CONFIG_HTTP_PASS = "app.config.http.pass";

    public static final String APP_CONFIG_DB_URL = "app.config.db.url";
    public static final String APP_CONFIG_DB_USER = "app.config.db.user";
    public static final String APP_CONFIG_DB_PASS = "app.config.db.pass";



}
