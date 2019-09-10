/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.ProvinceModel;
import io.id.app.model.RateModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class RateController extends BaseController {

    public RateController() {
        log = getLogger(this.getClass());
    }

    public boolean create(String rateName, String value) {
        boolean isCreate = false;
        final String methodName = "createNewRate";
        start(methodName);
        String sql = "INSERT INTO masterrate ( ratename ,  value ) VALUES (:name, :values)";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("name", rateName)
                    .bind("value", value);
            isCreate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreate;
    }

    public boolean edit(int rateId, String rateName, String value) {
        boolean isUpdate = false;
        final String methodName = "updateRate";
        start(methodName);
        String sql = "UPDATE masterrate SET ratename = :name, value = :values WHERE rateid = :id";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("id", rateId)
                    .bind("name", rateName)
                    .bind("value", value);
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

    public boolean delete(int rateId) {
        boolean isDelete = false;
        final String methodName = "deleteRate";
        start(methodName);
        String sql = "DELETE FROM masterrate WHERE rateid = :id";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("id", rateId);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public List<RateModel> getList() {
        List<RateModel> output = new ArrayList<>();
        final String methodName = "listRate";
        start(methodName);
        String sql = "SELECT rateid, ratename, value, created FROM masterrate ";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .mapToBean(RateModel.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public List<RateModel> get(int rateId) {
        List<RateModel> output = new ArrayList<>();
        final String methodName = "findRate";
        start(methodName);
        String sql = "SELECT rateid, ratename, value, created FROM masterrate WHERE rateid = :idd";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .bind("id", rateId)
                    .mapToBean(RateModel.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

}
