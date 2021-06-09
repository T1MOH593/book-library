package com.vlad.my_own_web_app.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private final List<Error> errors = new ArrayList<>();

    public ValidationResult add(Error error) {
        this.errors.add(error);
        return this;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
