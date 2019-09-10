package io.id.app.manager;

import io.id.app.util.log.AppLogger;

public class BaseManager {
    protected static AppLogger log;

    protected BaseManager() {
        // Empty Constructor
    }

    protected static AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }
    
    
}
