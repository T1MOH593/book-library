package com.vlad.my_own_web_app.unit.mapper;

import com.vlad.my_own_web_app.dto.BooksDto;
import com.vlad.my_own_web_app.entity.BookEntity;
import com.vlad.my_own_web_app.mapper.MapperFromBookEntityToAllBooksDto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@Tag("mapper")
@TestInstance(PER_CLASS)
public class MapperFromBookEntityToAllBooksDtoTest {

    private final MapperFromBookEntityToAllBooksDto mapper = MapperFromBookEntityToAllBooksDto.getInstance();

    @ParameterizedTest
    @MethodSource("com.vlad.my_own_web_app.unit.mapper.ArgumentsForMapperTests#getArgumentsForShouldCorrectlyMapToAllBooksDto")
    void shouldMapToAllBooksDto(List<BookEntity> bookEntities, BooksDto booksDto) {

        var result = mapper.map(bookEntities);

        assertThat(result).isEqualTo(booksDto);
    }
}
