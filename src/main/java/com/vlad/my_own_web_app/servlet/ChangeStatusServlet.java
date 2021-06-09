package com.vlad.my_own_web_app.servlet;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.service.BookService;
import com.vlad.my_own_web_app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.vlad.my_own_web_app.util.UrlPath.*;

@WebServlet(CHANGE_STATUS_PATH)
public class ChangeStatusServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("myBooks", bookService.getAllUsersBooksDto(String.valueOf(req.getSession().getAttribute("email"))).getAllBooks());
        req.getRequestDispatcher(JspHelper.getPath("change-status"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var titleAndAuthor = req.getParameter("titleAndAuthor");
        var titleAndAuthorSplit = titleAndAuthor.split("delimiter");
        var email = String.valueOf(req.getSession().getAttribute("email"));
        var bookDto = BookDto.builder()
                .author(titleAndAuthorSplit[1])
                .status(req.getParameter("status"))
                .title(titleAndAuthorSplit[0])
                .build();
        bookService.changeStatus(bookDto, email);
        resp.sendRedirect(MY_BOOKS_PATH);
    }
}
