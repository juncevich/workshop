package com.example.rabbitmq.tut6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
public class Tut6Client {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange exchange;


    private int start = 0;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        log.info(" [x] Requesting fib(" + start + ")");
        Integer response = (Integer) template.convertSendAndReceive
                (exchange.getName(), "rpc", start++);
        log.info(" [.] Got '" + response + "'");
    }
}
