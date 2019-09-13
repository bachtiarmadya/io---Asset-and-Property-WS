/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.UserDetailsModel;
import io.id.app.rest.model.RoleModel;
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

    public boolean add(String roleCode, String roleName, String description) {
        boolean isCreated = false;

        final String methodName = "addUser";
        start(methodName);
        final String QUERY = "INSERT INTO sysrole( rolecode, rolename, description, isactive) VALUES (:rolecode, :rolename, :description, 0)";
        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("rolecode", roleCode)
                    .bind("rolename", roleName)
                    .bind("description", description);
            isCreated = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreated;
    }

    public boolean update() {
        boolean isUpdate = false;

        return isUpdate;
    }

    public boolean delete() {
        boolean isDelete = false;

        return isDelete;
    }

    public List<RoleModel> find(String input) {
        List<RoleModel> output = new ArrayList<>();
        final String methodName = "getUserDetails";
        start(methodName);

        String sql = "";

        try ( Handle handle = getHandle()) {

            output = handle.createQuery(sql).bind("uname", input).mapToBean(RoleModel.class).list();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return output;
    }

    public List<RoleModel> getAll() {
        List<RoleModel> output = new ArrayList<>();
        final String methodName = "getRole";
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
