package io.id.app.controller;

import java.sql.SQLException;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import org.jdbi.v3.core.statement.Update;
import io.id.app.configuration.ApplicationConfigurationManager;
import io.id.app.manager.DBConnectionManager;
import io.id.app.util.log.AppLogger;

public class BaseController {

    protected AppLogger log;

    protected AppLogger getLogger(Class<?> clazz) {
        return new AppLogger(clazz);
    }

    protected String getConfig(String key) {
        return ApplicationConfigurationManager.getInstance().getConfiguration(key);
    }

    protected void start(String methodName) {
        log.debug(methodName, "Start");
    }

    protected void completed(String methodName) {
        log.debug(methodName, "Completed");
    }

    protected Handle getHandle() throws SQLException {
        return Jdbi.open(DBConnectionManager.getInstance().getDefaultConnection());
    }

    protected Handle getHandle(String dbName) throws SQLException {
        return Jdbi.open(DBConnectionManager.getInstance().getConnection(dbName));
    }

    protected boolean executeUpdate(Update update) {
        return update.execute() > 0;
    }

    protected boolean executeBatch(PreparedBatch batch) {
        int[] resultArr = batch.execute();

        for (int result : resultArr) {
            if (result < 0) {
                return false;
            }
        }
        return true;
    }

    protected <K> K getItem(List<K> itemList) {
        return itemList.isEmpty() ? null : itemList.get(0);
    }

}
