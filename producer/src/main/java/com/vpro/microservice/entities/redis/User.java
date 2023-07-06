package com.vpro.microservice.entities.redis;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.vpro.microservice.entities.IUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@RedisHash("User")
@Data
@AllArgsConstructor
public class User implements Serializable,IUser{
    private Long id;
    private String username;
    private Integer age;
}
