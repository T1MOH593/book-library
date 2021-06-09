package com.vlad.my_own_web_app.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessagesUtil {

    public static final String INVALID_STATUS = "Invalid status";
    public static final String INVALID_TITLE = "Invalid title";
    public static final String INVALID_AUTHOR = "Invalid author";
    public static final String INVALID_EMAIL = "Incorrect email or email is already in use";
    public static final String INVALID_NAME = "Put the correct name";
    public static final String INVALID_PASSWORD = "Length of password should be greater than 4";
    public static final String INVALID_EMAIL_STRUCTURE = "Email can only contain lowercase letters and _\napart from the required structure";
}
