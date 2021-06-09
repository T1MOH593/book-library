package com.vlad.my_own_web_app.mapper;

public interface Mapper<F, T> {

    T map(F object);
}
