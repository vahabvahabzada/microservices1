package com.vpro.microservice.repos.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vpro.microservice.entities.redis.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
}
