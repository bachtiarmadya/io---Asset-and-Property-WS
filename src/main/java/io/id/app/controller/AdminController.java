/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.MemberModel;
import io.id.app.model.UserModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class AdminController extends BaseController {

    public AdminController() {
        log = getLogger(this.getClass());
    }
    public List<UserModel> getAllUser() {
        List<UserModel> output = new ArrayList<>();
        final String methodName = "getListOfUser";
        start(methodName);

        final String sql = "SELECT * FROM `sysuser`;";

        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql).mapToBean(UserModel.class).list();
        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return output;
    }

    public List<MemberModel> getAllMember() {
        List<MemberModel> output = new ArrayList<>();
        final String methodName = "getListOfUser";
        start(methodName);
        final String sql = "SELECT * FROM masterdepartmentmember ;";

        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql).mapToBean(MemberModel.class).list();
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public boolean addNewMember(MemberModel input) {
        boolean isUpdate = false;
        final String methodName = "addNewMember";
        start(methodName);

        final String QUERY = "INSERT INTO masterdepartmentmember( membercode, membername, email, imageaddress, description, memberlevelid, departmentid, isactive) "
                + " VALUES "
                + " (:membercode, :membername, :email, :imageaddress, :description, :memberlevelid, :department, 0)";

        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("membercode", input.getMembercode())
                    .bind("membername", input.getMembername())
                    .bind("email", input.getEmail())
                    .bind("imageaddress", input.getImageaddress())
                    .bind("description", input.getImageaddress())
                    .bind("memberlevelid", input.getMemberlevelid())
                    .bind("department", input.getDepartmentid());
            isUpdate = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return isUpdate;
    }

    public boolean deleteMember(int id) {
        boolean isRemoved = false;
        final String methodName = "deleteMember";
        start(methodName);
        final String QUERY = "DELETE FROM masterdepartmentmember WHERE departmentmemberid = :id";

        try ( Handle handle = getHandle()) {
            Update update = handle.createUpdate(QUERY).bind("id", id);
            isRemoved = executeUpdate(update);
           
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isRemoved;
    }

    public boolean checkMemberCode(String memberCode) {
        boolean isExist = false;
        final String methodName = "checkMemberCode";
        start(methodName);
        final String QUERY = "SELECT COUNT(1) FROM masterdepartmentmember WHERE membercode = :membercode";

        try ( Handle handle = getHandle()) {
            int count = handle.createQuery(QUERY).bind("membercode", memberCode).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isExist = true;
            }

        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isExist;
    }
}
