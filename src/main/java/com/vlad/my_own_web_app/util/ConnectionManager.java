package com.vlad.my_own_web_app.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public class ConnectionManager {

    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";
    private static final String DRIVER_KEY = "db.driver";
    private static final String URL_KEY = "db.url";

    static {
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName(PropertiesUtil.get(DRIVER_KEY));
    }

    @SneakyThrows
    public static Connection get() {
        return DriverManager.getConnection(
                PropertiesUtil.get(URL_KEY),
                PropertiesUtil.get(USER_KEY),
                PropertiesUtil.get(PASSWORD_KEY)
        );
    }
}
