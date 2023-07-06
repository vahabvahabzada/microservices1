package com.vpro.microservice.communication;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.vpro.microservice.configs.MessageConfig;

@Component
public class Consumer {
    private String message;

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void handleMessage(String message) {
        System.out.println("Received : " + message);

        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}