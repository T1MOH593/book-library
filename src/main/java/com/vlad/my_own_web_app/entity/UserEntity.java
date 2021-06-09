package com.vlad.my_own_web_app.entity;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class UserEntity {

    private String email;
    private String name;
    private String password;
}
