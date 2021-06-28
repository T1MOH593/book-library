package com.vlad.my_own_web_app.unit.service;

import com.vlad.my_own_web_app.dao.BookDao;
import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.BookEntity;
import com.vlad.my_own_web_app.entity.Status;
import com.vlad.my_own_web_app.entity.UserEntity;
import com.vlad.my_own_web_app.exception.ValidationException;
import com.vlad.my_own_web_app.mapper.MapperFromBookDtoToBookEntity;
import com.vlad.my_own_web_app.mapper.MapperFromBookEntityToAllBooksDto;
import com.vlad.my_own_web_app.service.BookService;
import com.vlad.my_own_web_app.validator.BookDtoValidator;
import com.vlad.my_own_web_app.validator.Error;
import com.vlad.my_own_web_app.validator.ValidationResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
@Tag("service")
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookDao bookDao;
    @Mock
    private BookDtoValidator bookDtoValidator;
    @Mock
    private MapperFromBookDtoToBookEntity mapperFromBookDtoToBookEntity;
    @Mock
    private MapperFromBookEntityToAllBooksDto mapperFromBookEntityToBooksDto;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void prepare() {
        lenient().doReturn(new ValidationResult()).when(bookDtoValidator).isValid(BookDto.builder().build());

        lenient().doReturn(BookEntity.builder().build()).when(mapperFromBookDtoToBookEntity).map(BookDto.builder().build());
    }

    @Nested
    class AddBookMethod {

        @Test
        void shouldThrowValidationExceptionIfIncorrectBookDto() {
            var bookDto = BookDto.builder().author("dummy").status("dummy").title("dummy").build();

            doReturn(getInvalidValidationResult()).when(bookDtoValidator).isValid(bookDto);

            assertThrows(ValidationException.class, () -> bookService.addBook(bookDto, "dummy"));
        }

        @Test
        void shouldThrowValidationExceptionIfDidntSave() {

            doReturn(false).when(bookDao).save(BookEntity.builder().build(), "dummy");

            assertThrows(ValidationException.class, () -> bookService.addBook(BookDto.builder().build(), "dummy"));
        }

        private ValidationResult getInvalidValidationResult() {
            var validationResult = new ValidationResult();
            validationResult.add(Error.of("dummy"));

            return validationResult;
        }
    }

    @Test
    void shouldCorrectlyChangeStatusOfBook() {
        var bookDto = BookDto.builder().author("dummy").status("READING").title("dummy").build();
        doReturn(BookEntity.builder().author("dummy").status(Status.valueOf("READING")).title("dummy").build()).when(mapperFromBookDtoToBookEntity).map(bookDto);

        bookService.changeStatus(bookDto, "dummy");

        verify(bookDao).changeStatus(BookEntity.builder().author("dummy").status(Status.valueOf("READING")).title("dummy").build(), "dummy");
    }

    @Test
    void shouldCorrectlyDeleteBook() {
        var bookDto = BookDto.builder().author("dummy").status("READING").title("dummy").build();
        doReturn(BookEntity.builder().author("dummy").status(Status.valueOf("READING")).title("dummy").build()).when(mapperFromBookDtoToBookEntity).map(bookDto);

        bookService.deleteBook(bookDto, "dummy");

        verify(bookDao).delete(BookEntity.builder().author("dummy").status(Status.valueOf("READING")).title("dummy").build(), "dummy");
    }
}
