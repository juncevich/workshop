package com.example.rabbitmq.tut4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class Tut4Sender {

    private static final String[] keys = {"orange", "black", "green"};
    
    @Autowired
    private RabbitTemplate template;
    @Autowired
    private DirectExchange direct;
    private int index;
    private int count;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (++this.index == 3) {
            this.index = 0;
        }
        String key = keys[this.index];
        builder.append(key).append(' ');
        builder.append(Integer.toString(++this.count));
        String message = builder.toString();
        template.convertAndSend(direct.getName(), key, message);
        log.info(" [x] Sent '" + message + "'");
    }
}
