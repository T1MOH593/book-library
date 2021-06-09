package com.vlad.my_own_web_app.mapper;

import com.vlad.my_own_web_app.dto.BooksDto;
import com.vlad.my_own_web_app.entity.BookEntity;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.*;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class MapperFromBookEntityToAllBooksDto implements Mapper<List<BookEntity>, BooksDto> {

    private static final MapperFromBookEntityToAllBooksDto INSTANCE = new MapperFromBookEntityToAllBooksDto();

    @Override
    public BooksDto map(List<BookEntity> object) {
        var allBooksDto = new BooksDto();
        for (BookEntity bookEntity : object) {
            allBooksDto.add(getMapFromBookEntity(bookEntity));
        }
        return allBooksDto;
    }

    private static Map<String, String> getMapFromBookEntity(BookEntity book) {
        Map<String, String> map = new HashMap<>();
        var declaredFields = book.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                if (declaredField.get(book) != null) {
                    map.put(declaredField.getName(), String.valueOf(declaredField.get(book)));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static MapperFromBookEntityToAllBooksDto getInstance() {
        return INSTANCE;
    }


}
