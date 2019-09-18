/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.Mastervendor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class VendorController extends BaseController {

    public VendorController() {
        log = getLogger(this.getClass());
    }

    public boolean create(Mastervendor input) {
        boolean isCreate = false;
        final String methodName = "add";
        start(methodName);
        String sql = "INSERT INTO mastervendor (vendorcode, vendorname, contact, email, address, note, rateid) VALUES( :vendorcode, :vendorname, :contact, :email, :address, :note, :rateid);";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("vendorcode", input.getVendorcode())
                    .bind("vendorname", input.getVendorname())
                    .bind("contact", input.getContact())
                    .bind("email", input.getEmail())
                    .bind("address", input.getAddress())
                    .bind("note", input.getNote())
                    .bind("rateid", input.getRateid());
            isCreate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreate;
    }

    public boolean edit(Mastervendor input) {
        boolean isUpdate = false;
        final String methodName = "edit";
        start(methodName);
        String sql = "UPDATE mastervendor SET vendorcode = :vendorcode, vendorname= :vendorname, contact= :contact', email= :email, address= :address, note= :note, rateid= :rateid WHERE vendorid = :vendorid;";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("vendorid", input.getVendorid())
                    .bind("vendorcode", input.getVendorcode())
                    .bind("vendorname", input.getVendorname())
                    .bind("contact", input.getContact())
                    .bind("email", input.getEmail())
                    .bind("address", input.getAddress())
                    .bind("note", input.getNote())
                    .bind("rateid", input.getRateid());
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
        String sql = "DELETE FROM mastervendor WHERE vendorid = :vendorid";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("vendorid", id);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public List<Mastervendor> getList() {
        List<Mastervendor> output = new ArrayList<>();
        final String methodName = "getList";
        start(methodName);
        String sql = "SELECT * FROM mastervendor ";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .mapToBean(Mastervendor.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public List<Mastervendor> get(int id) {
        List<Mastervendor> output = new ArrayList<>();
        final String methodName = "get";
        start(methodName);
        String sql = "SELECT * FROM mastervendor WHERE vendorid = :vendorid";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .bind("vendorid", id)
                    .mapToBean(Mastervendor.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }


}
