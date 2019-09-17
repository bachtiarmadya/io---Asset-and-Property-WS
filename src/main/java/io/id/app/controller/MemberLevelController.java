/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.Mastermemberlevel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class MemberLevelController extends BaseController {

    public MemberLevelController() {
        log = getLogger(this.getClass());
    }

    public boolean add(Mastermemberlevel input) {
        boolean isCreated = false;

        final String methodName = "Add";
        start(methodName);
        final String QUERY = "INSERT INTO mastermemberlevel (levelcode, levelname, description, isactive) VALUES( :levelcode, :levelname, :description, 0);";
        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("levelcode", input.getLevelcode())
                    .bind("levelname", input.getLevelname())
                    .bind("description", input.getDescription());
            isCreated = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreated;
    }

    public boolean update(Mastermemberlevel input) {
        boolean isUpdate = false;
        final String methodName = "Update";
        start(methodName);
        String QUERY = "UPDATE mastermemberlevel SET levelcode = :levelcode, levelname = :levelname', description = :description WHERE memberlevelid = :memberlevelid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(QUERY)
                    .bind("memberlevelid", input.getMemberlevelid())
                    .bind("levelcode", input.getLevelcode())
                    .bind("levelname", input.getLevelname())
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
        String sql = "DELETE FROM mastermemberlevel WHERE memberlevelid = :memberlevelid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("memberlevelid", id);
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
        String sql = "UPDATE mastermemberlevel SET isactive = 1 WHERE memberlevelid = :memberlevelid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("memberlevelid", id);
            isActive = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isActive;
    }

    public List<Mastermemberlevel> getAll() {
        List<Mastermemberlevel> output = new ArrayList<>();
        final String methodName = "List";
        start(methodName);

        String sql = "SELECT * FROM mastermemberlevel";

        try ( Handle handle = getHandle()) {

            output = handle.createQuery(sql).mapToBean(Mastermemberlevel.class).list();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return output;
    }

}
