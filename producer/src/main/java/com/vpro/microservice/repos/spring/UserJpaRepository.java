package com.vpro.microservice.repos.spring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vpro.microservice.entities.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Long>{ 
}
