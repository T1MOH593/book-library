package com.vlad.my_own_web_app.util;

import com.vlad.my_own_web_app.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EmailChanger {

    public static String getChangedEmail(String email) {
        return email.replaceAll("[@.]", "").toLowerCase();
    }
}
