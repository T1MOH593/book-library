package com.vlad.my_own_web_app.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class UserDto {

    String name;
    String email;
    String password;
}
