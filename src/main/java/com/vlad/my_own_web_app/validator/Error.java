package com.vlad.my_own_web_app.validator;

import lombok.Value;

@Value(staticConstructor = "of")
public class Error {
    String errorMessage;
}
