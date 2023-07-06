package com.vpro.microservice.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vpro.microservice.dtos.UserDto;
import com.vpro.microservice.services.UserService;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) throws IOException, TimeoutException{
        return new ResponseEntity<UserDto>(userService.addUserToMQ(userDto),HttpStatus.OK);
    }

    @GetMapping("/recview")
    public ResponseEntity<List<UserDto>> recentlyViewed(){
        return new ResponseEntity<List<UserDto>>(userService.recent(), HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<UserDto>> viewAll(){
        return new ResponseEntity<List<UserDto>>(userService.viewAll(), HttpStatus.OK);
    }
}
