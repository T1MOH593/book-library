package com.vlad.my_own_web_app.validator;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.entity.Status;
import com.vlad.my_own_web_app.util.ErrorMessagesUtil;
import lombok.NoArgsConstructor;

import static com.vlad.my_own_web_app.util.ErrorMessagesUtil.*;
import static lombok.AccessLevel.*;

@NoArgsConstructor(access = PRIVATE)
public class BookDtoValidator implements Validator<BookDto> {

    private static final BookDtoValidator INSTANCE = new BookDtoValidator();

    @Override
    public ValidationResult isValid(BookDto object) {
        var validationResult = new ValidationResult();
        if (object.getAuthor().equals("") ||
        !isValid(object.getAuthor())) {
            validationResult.add(Error.of(INVALID_AUTHOR));
        }
        if (object.getTitle().equals("") ||
                !isValid(object.getTitle())) {
            validationResult.add(Error.of(INVALID_TITLE));
        }
        if (!object.getStatus().equals(Status.ALREADY_READ.name()) &&
                !object.getStatus().equals(Status.WANT_TO_READ.name()) &&
                !object.getStatus().equals(Status.READING.name())) {
            validationResult.add(Error.of(INVALID_STATUS));
        }
        return validationResult;
    }

    public boolean isValid(String string) {
        return !string.matches("^\s.*");
    }

    public static BookDtoValidator getInstance() {
        return INSTANCE;
    }
}
