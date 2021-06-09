package com.vlad.my_own_web_app.servlet;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.exception.ValidationException;
import com.vlad.my_own_web_app.service.BookService;
import com.vlad.my_own_web_app.util.ConnectionManager;
import com.vlad.my_own_web_app.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.vlad.my_own_web_app.util.UrlPath.*;

@WebServlet(ADD_BOOK_PATH)
public class AddBookServlet extends HttpServlet {

    private final BookService bookService = BookService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("add-book"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var title = req.getParameter("title");
        var author = req.getParameter("author");
        var status = req.getParameter("status");
        var email = String.valueOf(req.getSession().getAttribute("email"));
        var createBookDto = BookDto.builder()
                .author(author)
                .title(title)
                .status(status)
                .build();
        try {
            bookService.addBook(createBookDto, email);
            resp.sendRedirect(MY_BOOKS_PATH);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
