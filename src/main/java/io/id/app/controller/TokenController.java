/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.util.date.DateHelper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class TokenController extends BaseController {

    public TokenController() {
        log = getLogger(this.getClass());
    }

    public boolean save(String email, String token) {
        boolean isSaved = false;
        final String methodName = "save";
        start(methodName);
        final String sql = "INSERT INTO token (email,token) VALUES (:email,:token);";

        try (Handle h = getHandle()) {
            Update update = h.createUpdate(sql).bind("email", email).bind("token", token);
            isSaved = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);

        return isSaved;
    }

    public boolean validateToken(String email, String token, int expiry) {
        boolean isSaved = false;
        final String methodName = "validate";
        start(methodName);
        final String sql = "SELECT COUNT(1) FROM token WHERE email = :email AND token =:token AND createdt > :createDt;";

        try (Handle h = getHandle()) {
            LocalDateTime createDt = LocalDateTime.now().minusMinutes(expiry);
            int count = h.createQuery(sql).bind("email", email).bind("token", token)
                    .bind("createDt", DateHelper.formatDateTime(createDt)).mapTo(Integer.class).findOnly();

            if (count > 0) {
                isSaved = true;
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isSaved;
    }

    public boolean validateToken(String token, int expiry) {
        boolean isSaved = false;
        final String methodName = "validate";
        start(methodName);
        final String sql = "SELECT COUNT(1) FROM token WHERE token =:token AND createdt > :createDt;";

        try (Handle h = getHandle()) {
            LocalDateTime createDt = LocalDateTime.now().minusMinutes(expiry);
            int count = h.createQuery(sql).bind("token", token)
                    .bind("createDt", DateHelper.formatDateTime(createDt)).mapTo(Integer.class).findOnly();

            if (count > 0) {
                isSaved = true;
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isSaved;
    }

    public boolean validateToken(String token) {
        boolean isSaved = false;
        final String methodName = "validate";
        start(methodName);
        final String sql = "SELECT COUNT(1) FROM token WHERE token =:token;";

        try (Handle h = getHandle()) {

            int count = h.createQuery(sql).bind("token", token).mapTo(Integer.class).findOnly();

            if (count > 0) {
                isSaved = true;
            }

        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isSaved;
    }

    public String generateToken(String email) {
        String result = UUID.randomUUID().toString();
        result = DigestUtils.sha256Hex(email + result);
        return result;
    }

}
