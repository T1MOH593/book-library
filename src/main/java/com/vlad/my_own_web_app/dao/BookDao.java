package com.vlad.my_own_web_app.dao;

import com.vlad.my_own_web_app.entity.BookEntity;
import com.vlad.my_own_web_app.entity.Status;
import com.vlad.my_own_web_app.util.ConnectionManager;
import com.vlad.my_own_web_app.util.EmailChanger;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class BookDao {

    private static final BookDao INSTANCE = new BookDao();

    private static final String FIND_ALL_USERS_BOOKS_SQL = """
            SELECT title, author, status
            FROM users_and_books_storage.books
            JOIN books_of_users.%s ON users_and_books_storage.books.id = books_of_users.%s.book_id;
            """;
    private static final String SAVE_IN_BOOKS_SQL = """
            INSERT INTO users_and_books_storage.books (title, author)
            VALUES (?, ?)
            ON CONFLICT DO NOTHING 
            RETURNING id;
            """;
    private static final String GET_ID_SQL = """
            SELECT id
            FROM users_and_books_storage.books
            WHERE author = ? AND
                title = ?
            """;
    private static final String SAVE_IN_USERS_SQL = """
            INSERT INTO books_of_users.%s (book_id, status)
            VALUES (?, ?)
            ON CONFLICT DO NOTHING
            RETURNING book_id
            """;
    private static final String DELETE_SQL = """
            DELETE FROM books_of_users.%s
            WHERE book_id = ?
            """;
    private static final String FIND_BY_AUTHOR_AND_ID = """
            SELECT id
            FROM users_and_books_storage.books
            WHERE title = ?
            AND author =  ?
            """;
    private static final String CHANGE_STATUS_SQL = """
            UPDATE books_of_users.%s
            SET status = ?
            WHERE book_id = ?
            """;
    private static final String FIND_ALL_BOOKS_SQL = """
            SELECT title, author
            FROM users_and_books_storage.books
            """;

    @SneakyThrows
    public List<BookEntity> findAllUsersBooks(String email) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_USERS_BOOKS_SQL.formatted(
                     EmailChanger.getChangedEmail(email),
                     EmailChanger.getChangedEmail(email)))) {

            var resultSet = preparedStatement.executeQuery();

            List<BookEntity> bookEntities = new ArrayList<>();
            while (resultSet.next()) {
                bookEntities.add(BookEntity.builder()
                        .author(resultSet.getString("author"))
                        .title(resultSet.getString("title"))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
            return bookEntities;
        }
    }

    @SneakyThrows
    public boolean save(BookEntity bookEntity, String email) {
        try (var saveInBooksConnection = ConnectionManager.get();
             var saveInBooks = saveInBooksConnection.prepareStatement(SAVE_IN_BOOKS_SQL)) {
            saveInBooks.setString(1, bookEntity.getTitle());
            saveInBooks.setString(2, bookEntity.getAuthor());

            var idResultSet = saveInBooks.executeQuery();

            int id;
            if (idResultSet.next()) {
                id = idResultSet.getInt("id");
            } else {
                try (var getIdConnection = ConnectionManager.get();
                     var getIdStatement = getIdConnection.prepareStatement(GET_ID_SQL)) {
                    getIdStatement.setString(1, bookEntity.getAuthor());
                    getIdStatement.setString(2, bookEntity.getTitle());

                    var idResultSet2 = getIdStatement.executeQuery();
                    idResultSet2.next();
                    id = idResultSet2.getInt("id");
                }
            }
            try (var saveInUsersConnection = ConnectionManager.get();
                 var saveInUsersStatement = saveInUsersConnection.prepareStatement(SAVE_IN_USERS_SQL.formatted(email))) {
                saveInUsersStatement.setInt(1, id);
                saveInUsersStatement.setString(2, bookEntity.getStatus().name());

                var resultSet = saveInUsersStatement.executeQuery();

                return resultSet.next();
            }
        }
    }

    @SneakyThrows
    public void delete(BookEntity bookEntity, String email) {
        try (Connection getIdConnection = ConnectionManager.get();
             var getIdStatement = getIdConnection.prepareStatement(FIND_BY_AUTHOR_AND_ID)) {
            getIdStatement.setString(1, bookEntity.getTitle());
            getIdStatement.setString(2, bookEntity.getAuthor());

            var resultSet = getIdStatement.executeQuery();

            resultSet.next();
            var id = resultSet.getInt("id");
            try (var deleteBookConnection = ConnectionManager.get();
                 var deleteBookStatement = deleteBookConnection.prepareStatement(DELETE_SQL.formatted(email))) {
                deleteBookStatement.setInt(1, id);

                deleteBookStatement.executeUpdate();
            }
        }
    }

    @SneakyThrows
    public void changeStatus(BookEntity bookEntity, String email) {
        try (Connection getIdConnection = ConnectionManager.get();
             var getIdStatement = getIdConnection.prepareStatement(FIND_BY_AUTHOR_AND_ID)) {
            getIdStatement.setString(1, bookEntity.getTitle());
            getIdStatement.setString(2, bookEntity.getAuthor());

            var resultSet = getIdStatement.executeQuery();

            resultSet.next();
            var id = resultSet.getInt("id");
            try (var changeStatusConnection = ConnectionManager.get();
                 var changeStatusStatement = changeStatusConnection.prepareStatement(CHANGE_STATUS_SQL.formatted(email))) {
                changeStatusStatement.setString(1, bookEntity.getStatus().name());
                changeStatusStatement.setInt(2, id);

                changeStatusStatement.executeUpdate();
            }
        }
    }

    @SneakyThrows
    public List<BookEntity> findAllBooks() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_BOOKS_SQL)) {

            var resultSet = preparedStatement.executeQuery();

            List<BookEntity> bookEntities = new ArrayList<>();
            while (resultSet.next()) {
                bookEntities.add(BookEntity.builder()
                        .author(resultSet.getString("author"))
                        .title(resultSet.getString("title"))
                        .build());
            }

            return bookEntities;
        }
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }
}
