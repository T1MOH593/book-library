package com.vlad.my_own_web_app.unit.mapper;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.entity.BookEntity;
import com.vlad.my_own_web_app.mapper.MapperFromBookDtoToBookEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@Tag("mapper")
@TestInstance(PER_CLASS)
public class MapperFromBookDtoToBookEntityTest {

    private final MapperFromBookDtoToBookEntity mapper = MapperFromBookDtoToBookEntity.getInstance();



    @ParameterizedTest
    @MethodSource("com.vlad.my_own_web_app.unit.mapper.ArgumentsForMapperTests#getArgumentsForShouldCorrectlyMapToBookEntity")
    void shouldCorrectlyMapToBookEntity(BookDto bookDto, BookEntity bookEntity) {
        var result = mapper.map(bookDto);

        assertThat(result).isEqualTo(bookEntity);
    }
}
