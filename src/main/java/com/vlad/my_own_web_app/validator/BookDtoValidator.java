package com.vlad.my_own_web_app.validator;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.entity.Status;
import lombok.NoArgsConstructor;

import static com.vlad.my_own_web_app.util.ErrorMessagesUtil.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class BookDtoValidator implements Validator<BookDto> {

    private static final BookDtoValidator INSTANCE = new BookDtoValidator();

    @Override
    public ValidationResult isValid(BookDto object) {
        var validationResult = new ValidationResult();
        if (object.getAuthor() == null ||
                object.getAuthor().equals("") ||
                notValid(object.getAuthor())) {
            validationResult.add(Error.of(INVALID_AUTHOR));
        }
        if (object.getTitle() == null ||
                object.getTitle().equals("") ||
                notValid(object.getTitle())) {
            validationResult.add(Error.of(INVALID_TITLE));
        }
        if (object.getStatus() == null ||
                !object.getStatus().equals(Status.ALREADY_READ.name()) &&
                !object.getStatus().equals(Status.WANT_TO_READ.name()) &&
                !object.getStatus().equals(Status.READING.name())) {
            validationResult.add(Error.of(INVALID_STATUS));
        }
        return validationResult;
    }

    private boolean notValid(String string) {
        return !string.matches("^\\w.*");
    }

    public static BookDtoValidator getInstance() {
        return INSTANCE;
    }
}
