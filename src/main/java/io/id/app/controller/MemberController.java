/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import java.sql.SQLException;
import org.jdbi.v3.core.Handle;

/**
 *
 * @author permadi
 */
public class MemberController extends BaseController {

    public MemberController() {
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
    

}
