package com.vlad.my_own_web_app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookEntity {
    private String title;
    private String author;
    private Status status;
}
