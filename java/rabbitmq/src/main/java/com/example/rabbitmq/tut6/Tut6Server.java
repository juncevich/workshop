package com.example.rabbitmq.tut6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class Tut6Server {

    @RabbitListener(queues = "tut.rpc.requests")
    // @SendTo("tut.rpc.replies") used when the 
    // client doesn't set replyTo.
    public int fibonacci(int n) {
        log.info(" [x] Received request for " + n);
        int result = fib(n);
        log.info(" [.] Returned " + result);
        return result;
    }

    private int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }

}
