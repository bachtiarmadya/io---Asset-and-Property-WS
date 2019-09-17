/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.Masterdepartment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class DepartmentController extends BaseController {

    public DepartmentController() {
        log = getLogger(this.getClass());
    }

    public boolean add(Masterdepartment input) {
        boolean isCreated = false;

        final String methodName = "Add";
        start(methodName);
        final String QUERY = "INSERT INTO masterdepartment (departmentcode, departmentname, description, isactive) VALUES(:departmentcode, :departmentname, :description, 0);";
        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("departmentcode", input.getDepartmentcode())
                    .bind("departmentname", input.getDepartmentname())
                    .bind("description", input.getDescription());
            isCreated = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreated;
    }

    public boolean update(Masterdepartment input) {
        boolean isUpdate = false;
        final String methodName = "Update";
        start(methodName);
        String QUERY = "UPDATE masterdepartment SET departmentcode = :departmentcode, departmentname = :departmentname', description = :description WHERE departmentid = :departmentid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(QUERY)
                    .bind("departmentid", input.getDepartmentid())
                    .bind("departmentcode", input.getDepartmentcode())
                    .bind("departmentname", input.getDepartmentname())
                    .bind("description", input.getDescription());
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

    public boolean delete(int id) {
        boolean isDelete = false;
        final String methodName = "Delete";
        start(methodName);
        String sql = "DELETE FROM masterdepartment WHERE departmentid = :departmentid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("departmentid", id);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public boolean activate(int id) {
        boolean isActive = false;
        final String methodName = "Activate";
        start(methodName);
        String sql = "UPDATE masterdepartment SET isactive = 1 WHERE departmentid = :departmentid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("departmentid", id);
            isActive = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isActive;
    }

    public List<Masterdepartment> getAll() {
        List<Masterdepartment> output = new ArrayList<>();
        final String methodName = "List";
        start(methodName);

        String sql = "SELECT * FROM masterdepartment";

        try ( Handle handle = getHandle()) {

            output = handle.createQuery(sql).mapToBean(Masterdepartment.class).list();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return output;
    }

}
