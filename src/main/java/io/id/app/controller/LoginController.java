/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;


import java.util.List;
import java.util.Map;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class LoginController extends BaseController {

    public LoginController() {
        log = getLogger(this.getClass());
    }

    public String login(String uname, String password) {
        String output = null;
        final String methodName = "webAppLogin";
        start(methodName);
       final String userQuery = "SELECT roleid FROM sysuser WHERE username = :uname AND password = md5(:pwd)";
        final String roleQuery = "SELECT `rolecode` FROM `sysrole` WHERE `roleid` = :roleid";

        try (Handle handle = getHandle()) {
            List<Map<String, Object>> userResults = handle.createQuery(userQuery).bind("uname", uname).bind("pwd", password).mapToMap().list();
            List<Map<String, Object>> userList = userResults;

            for (Map<String, Object> listUser : userList) {

                int roleId = (Integer) listUser.get("roleid");
               
                List<Map<String, Object>> roleResults = handle.createQuery(roleQuery).bind("roleid", roleId).mapToMap().list();
                List<Map<String, Object>> roleList = roleResults;

                for (Map<String, Object> role : roleList) {
                    output = role.get("rolecode").toString();
                }
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }
    
    public String loginMobile(String uname, String password) {
        String output = null;
        final String methodName = "mobileLogin";
        start(methodName);
       final String userQuery = "SELECT departmentmemberid FROM sysuser WHERE username = :uname AND password = md5(:pwd)";
        final String roleQuery = "SELECT membercode FROM masterdepartmentmember WHERE departmentmemberid = :departmentmemberid";
 
        try (Handle handle = getHandle()) {
            List<Map<String, Object>> userResults = handle.createQuery(userQuery).bind("uname", uname).bind("pwd", password).mapToMap().list();
            List<Map<String, Object>> userList = userResults;

            for (Map<String, Object> listUser : userList) {

                int roleId = (Integer) listUser.get("departmentmemberid");
               
                List<Map<String, Object>> roleResults = handle.createQuery(roleQuery).bind("departmentmemberid", roleId).mapToMap().list();
                List<Map<String, Object>> roleList = roleResults;

                for (Map<String, Object> role : roleList) {
                    output = role.get("membercode").toString();
                }
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public boolean checkUsername(String username) {
        boolean isExist = false;
        final String methodName = "checkUsername";
        start(methodName);
        final String sql
                = "SELECT COUNT(1) FROM sysuser WHERE username = :username";

        try (Handle h = getHandle()) {
            int count = h.createQuery(sql).bind("username", username).mapTo(Integer.class
            ).findOnly();

            if (count > 0) {
                isExist = true;
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isExist;
    }

}
