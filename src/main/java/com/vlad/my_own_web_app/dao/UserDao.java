package com.vlad.my_own_web_app.dao;

import com.vlad.my_own_web_app.entity.UserEntity;
import com.vlad.my_own_web_app.util.ConnectionManager;
import com.vlad.my_own_web_app.util.EmailChanger;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users_and_books_storage.users(email, name, password)
            VALUES (?, ?, ?)
            """;
    private static final String FIND_BY_EMAIL_SQL = """
            SELECT email,
            name,
            password
            FROM users_and_books_storage.users
            WHERE email = ?
            """;
    private static final String FIND_BY_EMAIL_AND_PASSWORD = FIND_BY_EMAIL_SQL + "AND password = ?";
    private static final String FIND_ALL_SQL = """
            SELECT email,
            name,
            password
            FROM users_and_books_storage.users;
            """;
    private static final String UPDATE_SQL = """
            UPDATE users_and_books_storage.users
            SET name = ?,
            password = ?
            WHERE email = ?;
            """;
    private static final String DELETE_BY_EMAIL_SQL = """
            DELETE FROM users_and_books_storage.users
            WHERE email = ?
            RETURNING email;
            """;
    private static final String CREATE_USER_TABLE_SQL = """
            CREATE TABLE books_of_users.%s (
            book_id INTEGER REFERENCES users_and_books_storage.books (id),
            status VARCHAR(30) NOT NULL ,
            UNIQUE (book_id)
            );
            """;

    @SneakyThrows
    public void save(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL)) {
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getPassword());

            preparedStatement.executeUpdate();

        }
    }

    @SneakyThrows
    public Optional<UserEntity> findByEmail(String email) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
            preparedStatement.setString(1, email);

            var resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return Optional.of(UserEntity.builder()
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .build());
            }
            return Optional.empty();
        }
    }

    @SneakyThrows
    public List<UserEntity> findAll() {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            var resultSet = preparedStatement.executeQuery();

            List<UserEntity> userEntities = new ArrayList<>();
            while (resultSet.next()) {
                userEntities.add(UserEntity.builder()
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .email(resultSet.getString("email"))
                        .build());
            }
            return userEntities;
        }
    }

    @SneakyThrows
    public void update(UserEntity entity) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getEmail());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    public boolean deleteByEmail(String  email) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_BY_EMAIL_SQL)) {
            preparedStatement.setString(1, email);

            var resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    @SneakyThrows
    public Optional<UserEntity> findUser(UserEntity userEntity) {
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, userEntity.getEmail());
            preparedStatement.setString(2, userEntity.getPassword());

            var resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return Optional.of(UserEntity.builder()
                        .email(resultSet.getString("email"))
                        .name(resultSet.getString("name"))
                        .password(resultSet.getString("password"))
                        .build());
            }
            return Optional.empty();
        }
    }

    @SneakyThrows
    public void createUserTable(UserEntity userEntity) {
        String changedEmail = EmailChanger.getChangedEmail(userEntity.getEmail());
        try (Connection connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(CREATE_USER_TABLE_SQL.formatted(changedEmail))) {

            preparedStatement.executeUpdate();
        }
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
