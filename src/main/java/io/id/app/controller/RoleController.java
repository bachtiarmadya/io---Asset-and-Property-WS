/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.UserDetailsModel;
import io.id.app.rest.model.RoleModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class RoleController extends BaseController {

    public RoleController() {
        log = getLogger(this.getClass());
    }

    public boolean add(RoleModel role) {
        boolean isCreated = false;

        final String methodName = "AddRole";
        start(methodName);
        final String QUERY = "INSERT INTO sysrole( rolecode, rolename, description, isactive) VALUES (:rolecode, :rolename, :description, 0)";
        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("rolecode", role.getRolecode())
                    .bind("rolename", role.getRolename())
                    .bind("description", role.getDescription());
            isCreated = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreated;
    }

    public boolean update(RoleModel role) {
        boolean isUpdate = false;
        final String methodName = "UpdateRole";
        start(methodName);
        String sql = "UPDATE sysrole SET rolename = :rolename, description = :description WHERE roleid = :roleid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("roleid", role.getRoleid())
                    .bind("rolename", role.getRolename())
                    .bind("description", role.getDescription());
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

    public boolean delete(int id) {
        boolean isDelete = false;
        final String methodName = "DeleteRole";
        start(methodName);
        String sql = "DELETE FROM sysrole WHERE roleid = :id";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("id", id);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public boolean activate(int id) {
        boolean isActive = false;
        final String methodName = "ActivateRole";
        start(methodName);
        String sql = "UPDATE sysrole SET isactive = 1 WHERE roleid = :id";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("id", id);
            isActive = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isActive;
    }

    public List<RoleModel> getAll() {
        List<RoleModel> output = new ArrayList<>();
        final String methodName = "ListRole";
        start(methodName);

        String sql = "SELECT * FROM sysrole";

        try ( Handle handle = getHandle()) {

            output = handle.createQuery(sql).mapToBean(RoleModel.class).list();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return output;
    }

}
