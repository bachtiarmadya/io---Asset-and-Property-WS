package io.id.app.configuration;

public class ApplicationConfiguration {

    private ApplicationConfiguration() {
    }

    // API GATEWAY CONFIG
    public static final String API_GW_URL = "apigw.url";
    public static final String API_GW_CLIENT_ID = "apigw.client.id";
    public static final String API_GW_CLIENT_SECRET = "apigw.client.secret";
    public static final String API_GW_VALIDATE_TOKEN = "apigw.url.validate";

    //SMTP CONFIG
    public static final String MAIL_SMTP_USERNAME = "mail.smtp.username";
    public static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
    public static final String MAIL_SMTP_HOST = "mail.smtp.host";
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static final String MAIL_SMTP_STARTTTLS = "mail.smtp.starttls.enable";
    public static final String MAIL_SMTP_ADDRESS = "mail.smtp.address";

    // EMAIL TEMPLATE CONFIG
    public static final String EMAIL_SUBJECT = "email.subject";
    public static final String EMAIL_TEMPLATE = "email.body";
    public static final String EMAIL_CSS = "email.style.css";
    public static final String EMAIL_LINK = "email.link";
    public static final String EMAIL_JS = "email.js";

    // TOKEN CONFIG
    public static final String TOKEN_EXPIRY = "token.expiry";

}
