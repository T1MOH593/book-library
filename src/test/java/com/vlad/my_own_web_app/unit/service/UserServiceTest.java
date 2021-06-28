package com.vlad.my_own_web_app.unit.service;

import com.vlad.my_own_web_app.dao.UserDao;
import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.UserEntity;
import com.vlad.my_own_web_app.exception.ValidationException;
import com.vlad.my_own_web_app.mapper.MapperFromUserDtoToUserEntity;
import com.vlad.my_own_web_app.service.UserService;
import com.vlad.my_own_web_app.validator.Error;
import com.vlad.my_own_web_app.validator.UserDtoValidator;
import com.vlad.my_own_web_app.validator.ValidationResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;
import static org.mockito.Mockito.*;

@TestInstance(PER_CLASS)
@Tag("unit")
@Tag("service")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;
    @Mock
    private UserDtoValidator userDtoValidator;
    @Mock
    private MapperFromUserDtoToUserEntity mapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void prepare() {
        lenient().doReturn(new ValidationResult()).when(userDtoValidator).isValid(UserDto.builder().build());

        lenient().doReturn(UserEntity.builder().build()).when(mapper).map(UserDto.builder().build());


    }

    @Nested
    class CreateMethod {

        @Test
        void verifyDependenciesMethodCalls() {
            userService.create(UserDto.builder().build());

            verify(mapper, times(1)).map(UserDto.builder().build());

            verify(userDao, times(1)).save(UserEntity.builder().build());

            verify(userDao, times(1)).createUserTable(UserEntity.builder().build());
        }

        @Test
        void shouldThrowValidationExceptionWhenIncorrectUserDto() {

            var userDto = UserDto.builder().name("dummy").password("dummy").email("dummy@mail.ru").build();

            doReturn(getInvalidValidationResult()).when(userDtoValidator).isValid(userDto);

            assertThrows(ValidationException.class, () -> userService.create(userDto));
        }

        private ValidationResult getInvalidValidationResult() {
            var validationResult = new ValidationResult();
            validationResult.add(Error.of("dummy"));

            return validationResult;
        }
    }

    @Test
    void shouldThrowValidationExceptionIfIncorrectUserDto() {
        var userDto = UserDto.builder().name("dummy").password("dummy").email("dummy@mail.ru").build();
        var userEntity = UserEntity.builder().email("dummy@mail.ru").name("dummy").password("dummy").build();

        doReturn(Optional.empty()).when(userDao).findUser(userEntity);

        doReturn(userEntity).when(mapper).map(userDto);

        assertThrows(ValidationException.class, () -> userService.isLogged(userDto));
    }
}
