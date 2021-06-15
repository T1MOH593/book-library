package com.vlad.my_own_web_app.unit.validator;

import com.vlad.my_own_web_app.dao.UserDao;
import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.UserEntity;
import com.vlad.my_own_web_app.validator.UserDtoValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;
import static org.mockito.Mockito.*;

@Tag("validator")
@TestInstance(PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class UserDtoValidatorTest {

    private static final String TEST_EMAIL = "testtest@mail.com";

    @Mock
    private  UserDao userDao;
    @InjectMocks
    private final UserDtoValidator userDtoValidator = UserDtoValidator.getInstance();

    @BeforeEach
    void prepare() {
        lenient().doReturn(Optional.of(UserEntity.builder().build())).when(userDao).findByEmail(TEST_EMAIL);
    }

    @Disabled
    @ParameterizedTest
    @MethodSource("com.vlad.my_own_web_app.unit.validator.ArgumentsForValidatorTests#argumentsForShouldCorrectlyValidateUserDto")
    void shouldCorrectlyValidateUserDto(UserDto userDto, Boolean expectedResult) {

        var validationResult = userDtoValidator.isValid(userDto);

        assertThat(validationResult.isValid()).isEqualTo(expectedResult);
    }
}
