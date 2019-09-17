/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.Sysuser;
import io.id.app.model.UserDetailsModel;
import io.id.app.request.UserRequest;
import java.util.ArrayList;
import java.util.List;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class UserController extends BaseController {

    public UserController() {
        log = getLogger(this.getClass());
    }

    public List<UserDetailsModel> userDetails(UserRequest input) {
        List<UserDetailsModel> output = new ArrayList<>();
        final String methodName = "getUserDetails";
        start(methodName);

        String sql = "SELECT USER.username, MEMBER.membername, ROLE.rolename, MEMBER.membercode, MEMBER.email, MEMBER.imageaddress, POS.levelname, DEPT.departmentname "
                + " FROM sysuser USER "
                + " INNER JOIN sysrole ROLE ON ROLE.roleid = USER.roleid  "
                + " INNER JOIN masterdepartmentmember MEMBER ON MEMBER.departmentmemberid = USER.departmentmemberid "
                + " INNER JOIN mastermemberlevel POS ON POS.memberlevelid = MEMBER.memberlevelid "
                + " INNER JOIN masterdepartment DEPT ON DEPT.departmentid = MEMBER.departmentid "
                + " WHERE USER.username = :uname AND USER.password = md5(:pwd)";

        try ( Handle handle = getHandle()) {

            output = handle.createQuery(sql).bind("uname", input.getUname()).bind("pwd", input.getPassword()).mapToBean(UserDetailsModel.class).list();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return output;
    }

    public boolean checkEmail(String email) {
        boolean isExist = false;
        final String methodName = "checkEmail";
        start(methodName);
        final String sql
                = "SELECT COUNT(1) FROM masterdepartmentmember WHERE email = :email";

        try ( Handle h = getHandle()) {
            int count = h.createQuery(sql).bind("email", email).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isExist = true;
            }
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isExist;
    }

    public String checkUsername(String email) {
        String result = "";
        final String methodName = "checkUsername";
        start(methodName);
        final String sql
                = "SELECT USER.username "
                + "FROM sysuser USER "
                + " INNER JOIN masterdepartmentmember MEMBER ON MEMBER.membername = USER.alias "
                + " WHERE MEMBER.email = :email ";

        try ( Handle h = getHandle()) {
            result = h.createQuery(sql).bind("email", email).mapTo(String.class).findOnly();

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return result;
    }

    public boolean addUser(Sysuser user, int id) {
        boolean isCreated = false;
        final String methodName = "addUser";
        start(methodName);
        final String QUERY = "INSERT INTO sysuser( username, password, alias, roleid, departmentmemberid, isactive) "
                + " VALUES "
                + " (:username, md5(:password), :alias, :role, :department, 1)";
        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("username", user.getUsername())
                    .bind("password", user.getPassword())
                    .bind("alias", user.getAlias())
                    .bind("role", user.getRoleid())
                    .bind("department", id);
            isCreated = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isCreated;
    }

    public boolean activateMember(int memberId) {
        boolean isActive = false;
        final String methodName = "activateMember";
        start(methodName);
        final String QUERY = "UPDATE masterdepartmentmember SET isactive= 1 WHERE departmentmemberid = :id";
        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("id", memberId);
            isActive = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isActive;
    }

}
