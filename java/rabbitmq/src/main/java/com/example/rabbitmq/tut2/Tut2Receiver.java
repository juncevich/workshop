package com.example.rabbitmq.tut2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@Slf4j
@RabbitListener(queues = "hello")
public class Tut2Receiver {

    private final int instance;

    Tut2Receiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        log.info("instance " + this.instance +
                " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        log.info("instance " + this.instance +
                " [x] Done in: "+ in +" " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}