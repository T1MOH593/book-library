package com.vlad.my_own_web_app.validator;

import com.vlad.my_own_web_app.dao.UserDao;
import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.util.ErrorMessagesUtil;
import lombok.NoArgsConstructor;

import static com.vlad.my_own_web_app.util.ErrorMessagesUtil.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UserDtoValidator implements Validator<UserDto> {

    private static final UserDtoValidator INSTANCE = new UserDtoValidator();

    private final UserDao userDao = UserDao.getInstance();

    @Override
    public ValidationResult isValid(UserDto object) {
        var validationResult = new ValidationResult();
        if (object.getEmail().equals("")
                || !object.getEmail().contains("@")
                || !isEmailValid(object.getEmail())) {
            validationResult.add(Error.of(INVALID_EMAIL));
        }
        if (!EmailChecker.isEmailValid(object.getEmail())) {
            validationResult.add(Error.of(INVALID_EMAIL_STRUCTURE));
        }
        if (object.getName().equals("")) {
            validationResult.add(Error.of(INVALID_NAME));
        }
        if (object.getPassword().length() < 4) {
            validationResult.add(Error.of(INVALID_PASSWORD));
        }
        return validationResult;
    }

    private boolean isEmailValid(String email) {
        return userDao.findByEmail(email).isEmpty();
    }

    public static UserDtoValidator getInstance() {
        return INSTANCE;
    }
}
