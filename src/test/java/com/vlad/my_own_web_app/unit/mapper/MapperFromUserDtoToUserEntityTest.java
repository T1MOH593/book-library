package com.vlad.my_own_web_app.unit.mapper;

import com.vlad.my_own_web_app.dto.UserDto;
import com.vlad.my_own_web_app.entity.UserEntity;
import com.vlad.my_own_web_app.mapper.MapperFromUserDtoToUserEntity;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.*;

@Tag("mapper")
@Tag("unit")
@TestInstance(PER_CLASS)
public class MapperFromUserDtoToUserEntityTest {

    private final MapperFromUserDtoToUserEntity mapper = MapperFromUserDtoToUserEntity.getInstance();

    @ParameterizedTest
    @MethodSource("com.vlad.my_own_web_app.unit.mapper.ArgumentsForMapperTests#getArgumentsForShouldCorrectlyMapToUserEntity")
    void shouldMapToUserEntity(UserDto userDto, UserEntity userEntity) {
        var result = mapper.map(userDto);

        assertThat(result).isEqualTo(userEntity);
    }
}
