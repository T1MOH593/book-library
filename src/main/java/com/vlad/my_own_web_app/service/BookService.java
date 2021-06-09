package com.vlad.my_own_web_app.service;

import com.vlad.my_own_web_app.dao.BookDao;
import com.vlad.my_own_web_app.dto.BooksDto;
import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.exception.ValidationException;
import com.vlad.my_own_web_app.mapper.MapperFromBookEntityToAllBooksDto;
import com.vlad.my_own_web_app.mapper.MapperFromBookDtoToBookEntity;
import com.vlad.my_own_web_app.util.EmailChanger;
import com.vlad.my_own_web_app.validator.BookDtoValidator;
import com.vlad.my_own_web_app.validator.Error;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class BookService {

    private static final BookService INSTANCE = new BookService();

    private final BookDao bookDao = BookDao.getInstance();
    private final BookDtoValidator bookDtoValidator = BookDtoValidator.getInstance();
    private final MapperFromBookDtoToBookEntity mapperFromBookDtoToBookEntity = MapperFromBookDtoToBookEntity.getInstance();
    private final MapperFromBookEntityToAllBooksDto mapperFromBookEntityToBooksDto = MapperFromBookEntityToAllBooksDto.getInstance();

    public void addBook(BookDto bookDto, String email) {
        var validationResult = bookDtoValidator.isValid(bookDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var bookEntity = mapperFromBookDtoToBookEntity.map(bookDto);
        String changedEmail = EmailChanger.getChangedEmail(email);
        var result = bookDao.save(bookEntity, changedEmail);
        if (!result) {
            throw new ValidationException(validationResult.add(Error.of("Failed to insert book")).getErrors());
        }
    }

    public void changeStatus(BookDto bookDto, String email) {
        var bookEntity = mapperFromBookDtoToBookEntity.map(bookDto);
        var changedEmail = EmailChanger.getChangedEmail(email);
        bookDao.changeStatus(bookEntity, changedEmail);
    }

    public void deleteBook(BookDto bookDto, String email) {
        var changedEmail = EmailChanger.getChangedEmail(email);
        var bookEntity = mapperFromBookDtoToBookEntity.map(bookDto);
        bookDao.delete(bookEntity, changedEmail);
    }

    public BooksDto getAllUsersBooksDto(String email) {
        return mapperFromBookEntityToBooksDto.map(bookDao.findAllUsersBooks(email));
    }

    public BooksDto getAllBooksDto() {
        return mapperFromBookEntityToBooksDto.map(bookDao.findAllBooks());
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
