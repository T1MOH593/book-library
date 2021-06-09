package com.vlad.my_own_web_app.mapper;

import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.UserEntity;

public class MapperFromUserDtoToUserEntity implements Mapper<UserDto, UserEntity> {

    private static final MapperFromUserDtoToUserEntity INSTANCE = new MapperFromUserDtoToUserEntity();

    @Override
    public UserEntity map(UserDto object) {
        return UserEntity.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .build();
    }

    public static MapperFromUserDtoToUserEntity getInstance() {
        return INSTANCE;
    }
}
