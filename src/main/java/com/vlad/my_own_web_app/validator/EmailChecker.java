package com.vlad.my_own_web_app.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailChecker {

    public static boolean isEmailValid(String email) {
        return email.matches("^[a-z_]{1,15}@[a-z]{2,8}\\.[a-z]{1,5}$");
    }
}
