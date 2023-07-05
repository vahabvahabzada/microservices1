package com.vpro.microservice.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private Integer age;
}
