package com.vlad.my_own_web_app.unit.validator;

import com.vlad.my_own_web_app.dto.BookDto;
import com.vlad.my_own_web_app.validator.BookDtoValidator;
import com.vlad.my_own_web_app.validator.ValidationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@Tag("validator")
@Tag("unit")
@TestInstance(PER_CLASS)
public class BookDtoValidatorTest {

    private final BookDtoValidator bookDtoValidator = BookDtoValidator.getInstance();

    @ParameterizedTest
    @MethodSource("com.vlad.my_own_web_app.unit.validator.ArgumentsForValidatorTests#argumentsForShouldCorrectlyValidateBookDto")
    void shouldValidateBookDto(BookDto bookDto, Boolean expectedResult) {
        var validationResult = bookDtoValidator.isValid(bookDto);

        assertThat(validationResult.isValid()).isEqualTo(expectedResult);
    }
}
