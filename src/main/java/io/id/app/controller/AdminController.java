/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.model.MemberModel;
import io.id.app.model.UserModel;
import java.time.LocalDateTime;
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

    //   public List<Sysuser> getAllUserDetails() {
    //      List<Sysuser> output = new ArrayList<>();
//        final String methodName = "getDetailOfAllUser";
//        start(methodName);
//        final String userSql = "SELECT `userid`, `username`, `password`, `alias`, `roleid`, `departmentmemberid`, `isactive` FROM sysuser;";
//        final String roleSql = "SELECT `roleid`, `rolecode`, `rolename`, `description`, `isactive` FROM `sysrole` WHERE `roleid` = :roleId";
//        final String memberLevelSql = "SELECT `memberlevelid`, `levelcode`, `levelname`, `description`, `isactive` FROM `mastermemberlevel` WHERE `memberlevelid` = :memberlevelid";
//        final String departementSql = "SELECT `departmentid`, `departmentcode`, `departmentname`, `description`, `isactive` FROM `masterdepartment`WHERE `departmentid` = :departmentid";
//        final String deptMemberSql = "SELECT `departmentmemberid`, `membercode`, `membername`, `email`, `imageaddress`, `description`, `memberlevelid`, `departmentid`, `isactive` FROM `masterdepartmentmember` WHERE `departmentmemberid` = :departmentmemberid";
//
//        try (Handle handle = getHandle()) {
//            List<Map<String, Object>> userResults = handle.createQuery(userSql).mapToMap().list();
//            List<Map<String, Object>> userList = userResults;
//
//            for (Map<String, Object> listUser : userList) {
//                Sysuser userModel = new Sysuser();
//                userModel.setUserid((Integer) listUser.get("userid"));
//                userModel.setUsername(listUser.get("username").toString());
//                userModel.setPassword(listUser.get("password").toString());
//                userModel.setAlias(listUser.get("alias").toString());
//                int roleId = (Integer) listUser.get("roleid");
//
//                List<Sysrole> role = new ArrayList<>();
//                List<Map<String, Object>> roleResults = handle.createQuery(roleSql).bind("roleId", roleId).mapToMap().list();
//                List<Map<String, Object>> roleList = roleResults;
//
//                for (Map<String, Object> listRole : roleList) {
//                    Sysrole roleModel = new Sysrole();
//                    roleModel.setRoleid((Integer) listRole.get("roleid"));
//                    roleModel.setRolecode(listRole.get("rolecode").toString());
//                    roleModel.setRolename(listRole.get("rolename").toString());
//                    roleModel.setDescription(listRole.get("description").toString());
//                    roleModel.setIsactive((Integer) listRole.get("isactive"));
//                    role.add(roleModel);
//                }
//                userModel.setRole(role);
//
//                int memberId = (Integer) listUser.get("departmentmemberid");
//                List<Masterdepartmentmember> department = new ArrayList<>();
//                List<Map<String, Object>> departmentResults = handle.createQuery(deptMemberSql).bind("departmentmemberid", memberId).mapToMap().list();
//                List<Map<String, Object>> departmentList = departmentResults;
//                for (Map<String, Object> listDept : departmentList) {
//                    Masterdepartmentmember deptModel = new Masterdepartmentmember();
//
//                    deptModel.setDepartmentmemberid((Integer) listDept.get("departmentmemberid"));
//                    deptModel.setMemberCode(listDept.get("membercode").toString());
//                    deptModel.setMembername(listDept.get("membername").toString());
//                    deptModel.setEmail(listDept.get("email").toString());
//                    deptModel.setImageaddress(listDept.get("imageaddress").toString());
//                    deptModel.setDescription(listDept.get("description").toString());
//
//                    int levelId = (Integer) listDept.get("memberlevelid");
//                    List<Mastermemberlevel> member = new ArrayList<>();
//                    List<Map<String, Object>> levelResults = handle.createQuery(memberLevelSql).bind("memberlevelid", levelId).mapToMap().list();
//                    List<Map<String, Object>> levelList = levelResults;
//                    for (Map<String, Object> listLevel : levelList) {
//                        Mastermemberlevel levelModel = new Mastermemberlevel();
//                        levelModel.setMemberlevelid((Integer) listLevel.get("memberlevelid"));
//                        levelModel.setLevelcode(listLevel.get("levelcode").toString());
//                        levelModel.setLevelname(listLevel.get("levelname").toString());
//                        levelModel.setDescription(listLevel.get("description").toString());
//                        levelModel.setIsactive((Integer) listLevel.get("isactive"));
//                        member.add(levelModel);
//                    }
//                    deptModel.setMemberLevel(member);
//
//                    int dept = (Integer) listDept.get("departmentid");
//                    List<Masterdepartment> masterdepartment = new ArrayList<>();
//                    List<Map<String, Object>> masterdepartmentResults = handle.createQuery(departementSql).bind("departmentid", dept).mapToMap().list();
//                    List<Map<String, Object>> masterdepartmentList = masterdepartmentResults;
//                    for (Map<String, Object> listMasterdepartment : masterdepartmentList) {
//                        Masterdepartment masterDeptModel = new Masterdepartment();
//                        masterDeptModel.setDepartmentid((Integer) listMasterdepartment.get("departmentid"));
//                        masterDeptModel.setDepartmentcode(listMasterdepartment.get("departmentcode").toString());
//                        masterDeptModel.setDepartmentname(listMasterdepartment.get("departmentname").toString());
//                        masterDeptModel.setDescription(listMasterdepartment.get("description").toString());
//                        masterDeptModel.setIsactive((Integer) listMasterdepartment.get("isactive"));
//                        masterdepartment.add(masterDeptModel);
//                    }
//                    deptModel.setDepartment(masterdepartment);
//                    deptModel.setIsactive((Integer) listDept.get("isactive"));
//                    department.add(deptModel);
//                }
//
//                userModel.setDepartementMember(department);
//                userModel.setIsActive((Integer) listUser.get("isactive"));
//                output.add(userModel);
//
//            }
//        } catch (Exception ex) {
//            log.error(methodName, ex);
//        }
//
//        completed(methodName);
    //    return output;
    // }
    public List<UserModel> getAllUser() {
        List<UserModel> output = new ArrayList<>();
        final String methodName = "getListOfUser";
        start(methodName);

        final String sql = "SELECT `userid`, `username`, `password`, `alias`, `roleid`, `departmentmemberid`, `isactive` FROM `sysuser`;";

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
        final String sql = "SELECT departmentmemberid, membercode, membername, email, imageaddress, description, memberlevelid, departmentid, isactive FROM masterdepartmentmember ;";

        try ( Handle handle = getHandle()) {
            output = handle.createQuery(sql).mapToBean(MemberModel.class).list();
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return output;
    }

    public boolean addNewMember(String membercode, String membername, String email, String imageAddress, String description, int memberlevelid, int departmentid) {
        boolean isUpdate = false;
        final String methodName = "addNewMember";
        start(methodName);

        final String QUERY = "INSERT INTO masterdepartmentmember( membercode, membername, email, imageaddress, description, memberlevelid, departmentid, isactive) "
                + " VALUES "
                + " (:membercode, :membername, :email, :imageaddress, :description, :memberlevelid, :department, 1)";

        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("membercode", membercode)
                    .bind("membername", membername)
                    .bind("email", email)
                    .bind("imageaddress", imageAddress)
                    .bind("memberlevelid", description)
                    .bind("memberlevelid", memberlevelid)
                    .bind("department", departmentid);
            isUpdate = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return isUpdate;
    }
}
