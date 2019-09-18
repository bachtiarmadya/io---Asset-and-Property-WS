/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.Mastercategory;
import io.id.app.model.Mastertype;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class AssetTypeController extends BaseController {

    public AssetTypeController() {
        log = getLogger(this.getClass());
    }

    public boolean create(Mastertype input) {
        boolean isCreate = false;
        final String methodName = "add";
        start(methodName);
        String sql = "INSERT INTO mastertype (assettype, description, categoryid, isactive) VALUES( :assettype, :description, :categoryid, 0);";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("assettype", input.getAssettype())
                    .bind("description", input.getDescription())
                    .bind("categoryid", input.getCategoryid());
            isCreate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreate;
    }

    public boolean edit(Mastertype input) {
        boolean isUpdate = false;
        final String methodName = "edit";
        start(methodName);
        String sql = "UPDATE mastertype SET assettype = :assettype, description = :description, categoryid = :categoryid WHERE typeid = :typeid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("typeid", input.getTypeid())
                    .bind("assettype", input.getAssettype())
                    .bind("description", input.getDescription())
                    .bind("categoryid", input.getCategoryid());
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

    public boolean delete(int id) {
        boolean isDelete = false;
        final String methodName = "delete";
        start(methodName);
        String sql = "DELETE FROM mastertype WHERE typeid = :typeid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("typeid", id);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public List<Mastertype> getList() {
        List<Mastertype> output = new ArrayList<>();
        final String methodName = "getList";
        start(methodName);
        String sql = "SELECT * FROM mastertype ";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .mapToBean(Mastertype.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public List<Mastertype> get(int id) {
        List<Mastertype> output = new ArrayList<>();
        final String methodName = "get";
        start(methodName);
        String sql = "SELECT * FROM mastertype WHERE typeid = :typeid";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .bind("typeid", id)
                    .mapToBean(Mastertype.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public boolean activate(int id) {
        boolean isUpdate = false;
        final String methodName = "activate";
        start(methodName);
        String sql = "UPDATE mastertype SET isactive = 1 WHERE typeid = :typeid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("typeid", id);
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

}
