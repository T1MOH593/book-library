package com.vlad.my_own_web_app.mapper;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.entity.BookEntity;
import com.vlad.my_own_web_app.entity.Status;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class MapperFromBookDtoToBookEntity implements Mapper<BookDto, BookEntity> {

    private static final MapperFromBookDtoToBookEntity INSTANCE = new MapperFromBookDtoToBookEntity();

    @Override
    public BookEntity map(BookDto object) {
        return BookEntity.builder()
                .author(object.getAuthor())
                .title(object.getTitle())
                .status(object.getStatus() == null ? null : Status.valueOf(object.getStatus()))
                .build();
    }

    public static MapperFromBookDtoToBookEntity getInstance() {
        return INSTANCE;
    }
}
