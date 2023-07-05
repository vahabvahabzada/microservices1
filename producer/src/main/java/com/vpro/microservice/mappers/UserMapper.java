package com.vpro.microservice.mappers;

import org.springframework.stereotype.Component;

import com.vpro.microservice.dtos.UserDto;
import com.vpro.microservice.entities.User;

@Component
public class UserMapper {
    public User dtoToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        return user;
    }

    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        if (user.getId() != null) {
            userDto.setId(user.getId());
        }
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        return userDto;
    }
}
