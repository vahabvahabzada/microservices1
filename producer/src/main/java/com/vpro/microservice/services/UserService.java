package com.vpro.microservice.services;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;


import org.springframework.stereotype.Service;

import com.vpro.microservice.communication.Publisher;
import com.vpro.microservice.dtos.UserDto;
import com.vpro.microservice.entities.User;
import com.vpro.microservice.mappers.UserMapper;
import com.vpro.microservice.repos.redis.UserRepository;
import com.vpro.microservice.repos.spring.UserJpaRepository;

@Service
public class UserService {
    private UserMapper userMapper;
    private Publisher producer;
    private UserRepository userRepository;
    private UserJpaRepository userJpaRepository;
    public UserService(UserMapper userMapper, Publisher producer,UserRepository userRepository,UserJpaRepository userJpaRepository) {
        this.userMapper = userMapper;
        this.producer = producer;
        this.userRepository=userRepository;
        this.userJpaRepository=userJpaRepository;
    }

    public UserDto addUserToMQ(UserDto userDto) throws IOException, TimeoutException {
        User user = userMapper.dtoToEntity(userDto);
        return userMapper.entityToDto(producer.writeUser(user));
    }

    
    public List<UserDto> recent(){
        Iterator<com.vpro.microservice.entities.redis.User> recentlyWieved= userRepository.findAll().iterator();
        List<UserDto> recent=new LinkedList<>();
        while(recentlyWieved.hasNext()){
            recent.add(userMapper.entityToDto(recentlyWieved.next()));
        }
        return recent;
    }

    public List<UserDto> viewAll(){
        Iterator<User> all=userJpaRepository.findAll().iterator();
        List<UserDto> users=new LinkedList<>();
        User target;
        while(all.hasNext()){
            target=all.next();
            users.add(userMapper.entityToDto(target));
            userRepository.save(new com.vpro.microservice.entities.redis.User(target.getId(),target.getUsername(),target.getAge()));
        }

        return users;
    }
}
