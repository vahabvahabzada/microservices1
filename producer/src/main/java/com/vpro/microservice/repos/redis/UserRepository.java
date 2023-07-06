package com.vpro.microservice.repos.redis;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.vpro.microservice.entities.redis.User;

@Repository
public class UserRepository {
    public static final String HASH_KEY = "User";
    private RedisTemplate<String,User> redisTemplate;

    public UserRepository(RedisTemplate<String,User> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User save(User user) {
        redisTemplate.opsForHash().put(HASH_KEY, user.getId(), user);
        return user;
    }

    public List<User> findAll() {
        //return redisTemplate.opsForHash().values(HASH_KEY);
        return redisTemplate.opsForHash().values(HASH_KEY).stream().map(user->(User) user).toList();
    }
}
