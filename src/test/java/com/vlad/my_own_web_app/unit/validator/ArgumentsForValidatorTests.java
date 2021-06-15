package com.vlad.my_own_web_app.unit.validator;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.dto.UserDto;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

@UtilityClass
class ArgumentsForValidatorTests {

    static Stream<Arguments> argumentsForShouldCorrectlyValidateBookDto() {
        var bookDto1 = BookDto.builder().status("READING").title("dummy").author("dummy").build();
        var bookDto2 = BookDto.builder().status("dummy").title("null").author("null").build();
        var bookDto3 = BookDto.builder().title("dummy").author("dummy").build();
        var bookDto4 = BookDto.builder().status("READING").author("dummy").build();
        var bookDto5 = BookDto.builder().status("READING").title("dummy").build();
        var bookDto6 = BookDto.builder().status("READING").title("").author("dummy").build();
        var bookDto7 = BookDto.builder().status("READING").title(". ").author("dummy").build();
        var bookDto8 = BookDto.builder().status("READING").title("dummy").author(" .").build();
        var bookDto9 = BookDto.builder().status("READING").title("dummy").author("").build();

        return Stream.of(
                Arguments.of(bookDto1, Boolean.TRUE),
                Arguments.of(bookDto2, Boolean.FALSE),
                Arguments.of(bookDto3, Boolean.FALSE),
                Arguments.of(bookDto4, Boolean.FALSE),
                Arguments.of(bookDto5, Boolean.FALSE),
                Arguments.of(bookDto6, Boolean.FALSE),
                Arguments.of(bookDto7, Boolean.FALSE),
                Arguments.of(bookDto8, Boolean.FALSE),
                Arguments.of(bookDto9, Boolean.FALSE)
        );
    }

    static Stream<Arguments> argumentsForShouldCorrectlyValidateUserDto() {
        var userDto1 = UserDto.builder().email("testtest@mail.com").password("dummy").name("dummy").build();
        var userDto2 = UserDto.builder().email("testtestmail.com").password("dummy").name("dummy").build();
        var userDto3 = UserDto.builder().email("testtest@mailcom").password("dummy").name("dummy").build();
        var userDto4 = UserDto.builder().email("testtest@mail.com").name("test").build();
        var userDto5 = UserDto.builder().password("12345").name("test").build();
        var userDto6 = UserDto.builder().email("testtest@mail.com").password("12345").build();
        var userDto7 = UserDto.builder().email("testtest@mail.com").password("").name("dummy").build();
        var userDto8 = UserDto.builder().email("testtest@mail.com").password("dummy").name("").build();
        var userDto9 = UserDto.builder().email("testtest@mail.com").password("dummy").name("   ").build();

        return Stream.of(
                Arguments.of(userDto1, Boolean.TRUE),
                Arguments.of(userDto2, Boolean.FALSE),
                Arguments.of(userDto3, Boolean.FALSE),
                Arguments.of(userDto4, Boolean.FALSE),
                Arguments.of(userDto5, Boolean.FALSE),
                Arguments.of(userDto6, Boolean.FALSE),
                Arguments.of(userDto7, Boolean.FALSE),
                Arguments.of(userDto8, Boolean.FALSE),
                Arguments.of(userDto9, Boolean.FALSE)
        );
    }
}
