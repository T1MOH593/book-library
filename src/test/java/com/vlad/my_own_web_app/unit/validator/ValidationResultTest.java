package com.vlad.my_own_web_app.unit.validator;

import com.vlad.my_own_web_app.validator.Error;
import com.vlad.my_own_web_app.validator.ValidationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@Tag("validator")
@Tag("unit")
@TestInstance(PER_CLASS)
public class ValidationResultTest {

    @Test
    void shouldAddAndValidate() {
        var validationResult = new ValidationResult();
        validationResult.add(Error.of("dummy"));

        assertAll(
                () -> assertThat(validationResult.getErrors().size()).isEqualTo(1),
                () -> assertThat(validationResult.isValid()).isFalse()
        );
    }
}
