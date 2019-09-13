/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;


import io.id.app.model.ProvinceModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class ProvinceController extends BaseController {

    public ProvinceController() {
        log = getLogger(this.getClass());
    }

    public boolean create(String provinceCode, String provinceName) {
        boolean isCreate = false;
        final String methodName = "createNewProvince";
        start(methodName);
        String sql = "INSERT INTO masterprovince ( provincecode, provincename, isactive) VALUES (:code, :name, 0)";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("code", provinceCode)
                    .bind("name", provinceName);
            isCreate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreate;
    }

    public boolean update(int provinceId, String provinceCode, String provinceName, int isActive) {
        boolean isUpdate = false;
        final String methodName = "updateProvince";
        start(methodName);
        String sql = "UPDATE masterprovince SET provincecode= :code, provincename = :name, isactive = :active WHERE provinceid = :id";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("id", provinceId)
                    .bind("code", provinceCode)
                    .bind("name", provinceName)
                    .bind("active", isActive);
            isUpdate = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isUpdate;
    }

    public boolean delete(int provinceId) {
        boolean isDelete = false;
        final String methodName = "deleteProvince";
        start(methodName);
        String sql = "DELETE FROM masterprovince WHERE provinceid = :id";
        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(sql)
                    .bind("id", provinceId);
            isDelete = executeUpdate(update);
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isDelete;
    }

    public List<ProvinceModel> getList() {
        List<ProvinceModel> output = new ArrayList<>();
        final String methodName = "listProvince";
        start(methodName);
        String sql = "SELECT provinceid, provincecode, provincename, isactive FROM masterprovince";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql).mapToBean(ProvinceModel.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }
    public List<ProvinceModel> find(int provinceId) {
        List<ProvinceModel> output = new ArrayList<>();
        final String methodName = "findProvince";
        start(methodName);
        String sql = "SELECT provinceid, provincecode, provincename, isactive FROM masterprovince WHERE provinceid = :id";
        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql)
                    .bind("id", provinceId)
                    .mapToBean(ProvinceModel.class).list();
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

}
