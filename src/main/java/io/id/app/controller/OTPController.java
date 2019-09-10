/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.id.app.controller;

import io.id.app.configuration.ApplicationConfiguration;
import io.id.app.util.date.DateHelper;
import java.time.LocalDateTime;
import java.util.Random;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.Update;

/**
 *
 * @author permadi
 */
public class OTPController extends BaseController {

    public OTPController() {
        log = getLogger(this.getClass());
    }

    public String getSubject() {
        String subject = getConfig(ApplicationConfiguration.OTP_SUBJECT);
        return subject;
    }

    public String getBody(String email, String code) {
        String css = getConfig(ApplicationConfiguration.OTP_CSS);
        String template = getConfig(ApplicationConfiguration.OTP_BODY);
        String result = template
                .replace("[style_css]", css)
                .replace("[email]", email)
                .replace("[otp]", code);
        return result;
    }

    public String generateOTP(int length) {
        String result = null;
        final String methodName = "generateOtp";
        start(methodName);
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[length];

        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
            result = String.valueOf(otp);
        }
        completed(methodName);
        return result;
    }

    public boolean saveOtp(String email, String code) {
        boolean isSaved = false;
        final String methodName = "saveOtp";
        start(methodName);

        final String QUERY = "INSERT INTO otp (email, code) VALUES (:email, :code)";

        try ( Handle h = getHandle()) {
            Update update = h.createUpdate(QUERY)
                    .bind("email", email)
                    .bind("code", code);
            isSaved = executeUpdate(update);
        } catch (Exception ex) {
            log.error(methodName, ex);
        }

        completed(methodName);
        return isSaved;
    }

    public boolean validateOtp(String email, String code, int expiry) {

        boolean isValid = false;
        final String methodName = "validateOtp";
        start(methodName);
        final String QUERY = "SELECT COUNT(1) FROM otp WHERE email =:email AND code =:code AND create_dt > :expiry;";

        try ( Handle h = getHandle()) {
            LocalDateTime createDt = LocalDateTime.now().minusMinutes(expiry);
            int count = h.createQuery(QUERY)
                    .bind("email", email)
                    .bind("code", code)
                    .bind("expiry", DateHelper.formatDateTime(createDt)).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isValid = true;
            }
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isValid;

    }

    public boolean validateOtp(String code, int expiry) {

        boolean isValid = false;
        final String methodName = "validateOtp";
        start(methodName);
        final String QUERY = "SELECT COUNT(1) FROM otp WHERE code =:code AND create_dt > :expiry;";

        try ( Handle h = getHandle()) {
            LocalDateTime createDt = LocalDateTime.now().minusMinutes(expiry);
            int count = h.createQuery(QUERY)
                    .bind("code", code)
                    .bind("expiry", DateHelper.formatDateTime(createDt)).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isValid = true;
            }
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isValid;

    }

    public boolean validateOtp(String code) {

        boolean isValid = false;
        final String methodName = "validateOtp";
        start(methodName);
        final String QUERY = "SELECT COUNT(1) FROM otp WHERE  code =:code ;";

        try ( Handle h = getHandle()) {

            int count = h.createQuery(QUERY).bind("code", code).mapTo(Integer.class).findOnly();
            if (count > 0) {
                isValid = true;
            }
        } catch (Exception ex) {
            log.error(methodName, ex);
        }
        completed(methodName);
        return isValid;

    }
}
