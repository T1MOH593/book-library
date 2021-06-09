package com.vlad.my_own_web_app.servlet;

import com.vlad.my_own_web_app.service.BookService;
import com.vlad.my_own_web_app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.vlad.my_own_web_app.util.UrlPath.*;

@WebServlet(MY_BOOKS_PATH)
public class MyBooksServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myBooks", bookService.getAllUsersBooksDto(String.valueOf(req.getSession().getAttribute("email"))).getAllBooks());
        req.getRequestDispatcher(JspHelper.getPath(MY_BOOKS_PATH))
                .forward(req, resp);
    }

}
