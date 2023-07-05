package com.vpro.microservice.services;

import org.springframework.stereotype.Service;

import com.vpro.microservice.communication.Consumer;

@Service
public class UserService {
    private Consumer consumer;

    public UserService(Consumer consumer) {
        this.consumer = consumer;
    }

    public String getFromMQ() {
        return consumer.getProcessedMessage();
    }
}
