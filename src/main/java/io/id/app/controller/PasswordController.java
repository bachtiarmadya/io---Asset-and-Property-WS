/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.configuration.ApplicationConfiguration;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class PasswordController extends BaseController {

    public PasswordController() {
        log = getLogger(this.getClass());
    }

    public String getSubject() {
        String subject = getConfig(ApplicationConfiguration.EMAIL_SUBJECT);
        return subject;
    }

    public String getBody(String email, String token) {
        String css = getConfig(ApplicationConfiguration.EMAIL_CSS);
        String link = getConfig(ApplicationConfiguration.EMAIL_LINK);
        String javascript = getConfig(ApplicationConfiguration.EMAIL_JS);
        String template = getConfig(ApplicationConfiguration.EMAIL_TEMPLATE);
        String result = template
                .replace("[style_css]", css)
                .replace("[javasript]", javascript)
                .replace("[name]", email)
                .replace("[generated_link]", link + token);
        return result;
    }

    public boolean resetPassword(String username, String password) {
        boolean isReset = false;
        final String methodName = "resetPassword";
        start(methodName);
        final String sql = "UPDATE sysuser SET password=md5(:password) WHERE username = :username";

        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(sql).bind("password", password).bind("username", username);
            isReset = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isReset;
    }

    public boolean changePassword(String email, String oldPassword, String newPassword) {
        boolean isChanged = false;
        final String methodName = "changePassword";
        start(methodName);
        final String sql = "UPDATE TableUser SET password = md5(:newPassword) WHERE email = :email AND password = md5(:oldPassword);";

        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(sql).bind("email", email).bind("oldPassword", oldPassword).bind("newPassword", newPassword);
            isChanged = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isChanged;
    }

}
