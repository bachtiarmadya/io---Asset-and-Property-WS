/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class MemberActivationController extends BaseController {

    public MemberActivationController() {
        log = getLogger(this.getClass());
    }

    public boolean checkMember(String email) {
        boolean isExist = false;
        final String methodName = "checkMemberCode";
        start(methodName);
        final String QUERY = "SELECT COUNT(1) FROM masterdepartmentmember WHERE email = :email AND isactive = 0";

        try ( Handle handle = getHandle()) {
            int count = handle.createQuery(QUERY).bind("email", email).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isExist = true;
            }
        } catch (SQLException ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isExist;
    }

    public String getMemberId(String email) {
        String result = null;
        final String methodName = "getMemberId";
        start(methodName);
        final String QUERY = "SELECT departmentmemberid AS memberid FROM masterdepartmentmember WHERE email = :email";

        try ( Handle handle = getHandle()) {
            List<Map<String, Object>> userResults = handle.createQuery(QUERY).bind("email", email).mapToMap().list();
            List<Map<String, Object>> userList = userResults;
            for (Map<String, Object> listUser : userList) {
                result = listUser.get("memberid").toString();
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return result;
    }
}
