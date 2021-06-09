package com.vlad.my_own_web_app.service;

import com.vlad.my_own_web_app.dao.UserDao;
import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.exception.ValidationException;
import com.vlad.my_own_web_app.mapper.MapperFromUserDtoToUserEntity;
import com.vlad.my_own_web_app.validator.UserDtoValidator;
import com.vlad.my_own_web_app.validator.Error;
import com.vlad.my_own_web_app.validator.ValidationResult;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();
    private final UserDtoValidator userDtoValidator = UserDtoValidator.getInstance();
    private final MapperFromUserDtoToUserEntity mapperFromUserDtoToUserEntity = MapperFromUserDtoToUserEntity.getInstance();

    public void create(UserDto userDto) {
        var validationResult = userDtoValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = mapperFromUserDtoToUserEntity.map(userDto);
        userDao.save(userEntity);
        userDao.createUserTable(userEntity);

    }

    public void isLogged(UserDto userDto) {
        var validationResult = new ValidationResult();
        var userEntity = mapperFromUserDtoToUserEntity.map(userDto);
        if (userDao.findUser(userEntity)
                .isEmpty()) {
            validationResult.add(Error.of("Incorrect email or/and password"));
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
