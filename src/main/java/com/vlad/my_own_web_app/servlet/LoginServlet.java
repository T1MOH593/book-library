package com.vlad.my_own_web_app.servlet;

import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.UserEntity;
import com.vlad.my_own_web_app.exception.ValidationException;
import com.vlad.my_own_web_app.service.UserService;
import com.vlad.my_own_web_app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.vlad.my_own_web_app.util.UrlPath.*;

@WebServlet(LOGIN_PATH)
public class LoginServlet  extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = UserDto.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
        try {
            userService.isLogged(userDto);
            req.getSession().setAttribute("email", userDto.getEmail());
            resp.sendRedirect(MY_BOOKS_PATH);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }


}
