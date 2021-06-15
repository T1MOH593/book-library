package com.vlad.my_own_web_app.unit.mapper;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.dto.BooksDto;
import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.BookEntity;
import com.vlad.my_own_web_app.entity.Status;
import com.vlad.my_own_web_app.entity.UserEntity;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@UtilityClass
class ArgumentsForMapperTests {

    static Stream<Arguments> getArgumentsForShouldCorrectlyMapToBookEntity() {
        var bookDto1 = BookDto.builder().author("Nassim Nicolas Taleb").title("Black swan").status(Status.ALREADY_READ.name()).build();
        var bookDto2 = BookDto.builder().author("Nassim Nicolas Taleb").title("Black swan").status(null).build();

        var bookEntity1 = BookEntity.builder().author("Nassim Nicolas Taleb").title("Black swan").status(Status.ALREADY_READ).build();
        var bookEntity2 = BookEntity.builder().author("Nassim Nicolas Taleb").title("Black swan").status(null).build();
        return Stream.of(
                Arguments.of(bookDto1, bookEntity1),
                Arguments.of(bookDto2, bookEntity2)
        );
    }

    static Stream<Arguments> getArgumentsForShouldCorrectlyMapToUserEntity() {
        var userDto1 = UserDto.builder().password("password1").email("email1").name("name1").build();
        var userDto2 = UserDto.builder().password("password2").email("email2").name("name2").build();

        var userEntity1 = UserEntity.builder().password("password1").email("email1").name("name1").build();
        var userEntity2 = UserEntity.builder().password("password2").email("email2").name("name2").build();

        return Stream.of(
                Arguments.of(userDto1, userEntity1),
                Arguments.of(userDto2, userEntity2)
        );
    }

    static Stream<Arguments> getArgumentsForShouldCorrectlyMapToAllBooksDto() {
        var book1 = BookEntity.builder().author("dummy").title("dummy").status(Status.ALREADY_READ).build();
        var book2 = BookEntity.builder().title("mock").author("mock").status(Status.READING).build();

        var booksDto = new BooksDto();
        booksDto.add(Map.of("author", "dummy", "title", "dummy", "status", "ALREADY_READ"));
        booksDto.add(Map.of("author", "mock", "title", "mock", "status", "READING"));
        return Stream.of(
                Arguments.of(List.of(book1, book2), booksDto)
        );
    }
}
