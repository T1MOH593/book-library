package com.vlad.my_own_web_app.dto;

import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Value
public class BooksDto {

    @Getter
    List<Map<String, String>> allBooks = new ArrayList<>();

    public void add(Map<String, String> user) {
        allBooks.add(user);
    }
}
