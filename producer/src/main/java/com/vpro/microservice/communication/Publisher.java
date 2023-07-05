package com.vpro.microservice.communication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.vpro.microservice.configs.MessageConfig;
import com.vpro.microservice.entities.User;

@Component
public class Publisher {
    private RabbitTemplate rabbitTemplate;

    public Publisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public User writeUser(User user) throws IOException, TimeoutException {
        rabbitTemplate.convertAndSend(MessageConfig.QUEUE, user.toString());
        return user;
    }
}
