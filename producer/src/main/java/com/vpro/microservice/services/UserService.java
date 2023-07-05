package com.vpro.microservice.services;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;

import com.vpro.microservice.communication.Publisher;
import com.vpro.microservice.dtos.UserDto;
import com.vpro.microservice.entities.User;
import com.vpro.microservice.mappers.UserMapper;

@Service
public class UserService {
    private UserMapper userMapper;
    private Publisher producer;

    public UserService(UserMapper userMapper, Publisher producer) {
        this.userMapper = userMapper;
        this.producer = producer;
    }

    public UserDto addUserToMQ(UserDto userDto) throws IOException, TimeoutException {
        User user = userMapper.dtoToEntity(userDto);
        return userMapper.entityToDto(producer.writeUser(user));
    }
}
