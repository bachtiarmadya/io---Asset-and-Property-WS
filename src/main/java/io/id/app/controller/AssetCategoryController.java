/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.Mastercategory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.eclipse.persistence.jpa.jpql.JPAVersion.value;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class AssetCategoryController extends BaseController {

    public AssetCategoryController() {
        log = getLogger(this.getClass());
    }

    public boolean create(Mastercategory input) {
        boolean isCreate = false;
        final String methodName = "add";
        start(methodName);
        String sql = "INSERT INTO mastercategory (assetcategory, description, isactive) VALUES( :assetcategory, :description, 0);";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("assetcategory", input.getAssetcategory())
                    .bind("description", input.getDescription());
            isCreate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreate;
    }

    public boolean edit(Mastercategory input) {
        boolean isUpdate = false;
        final String methodName = "edit";
        start(methodName);
        String sql = "UPDATE mastercategory SET assetcategory = :assetcategory, description = :description  WHERE categoryid = :categoryid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("categoryid", input.getCategoryid())
                    .bind("assetcategory", input.getAssetcategory())
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
        final String methodName = "delete";
        start(methodName);
        String sql = "DELETE FROM mastercategory WHERE categoryid = :categoryid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("categoryid", id);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public List<Mastercategory> getList() {
        List<Mastercategory> output = new ArrayList<>();
        final String methodName = "getList";
        start(methodName);
        String sql = "SELECT * FROM mastercategory ";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .mapToBean(Mastercategory.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public List<Mastercategory> get(int id) {
        List<Mastercategory> output = new ArrayList<>();
        final String methodName = "get";
        start(methodName);
        String sql = "SELECT * FROM mastercategory WHERE categoryid = :categoryid";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .bind("categoryid", id)
                    .mapToBean(Mastercategory.class).list();
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
        String sql = "UPDATE mastercategory SET isactive = 1 WHERE categoryid = :categoryid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("categoryid", id);
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

}
