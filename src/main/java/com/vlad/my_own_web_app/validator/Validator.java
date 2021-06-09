package com.vlad.my_own_web_app.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
