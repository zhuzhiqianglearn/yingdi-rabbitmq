package com.example.consummer.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanout.A")
public class FanoutReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("fanout.A : " + testMessage.toString());
    }

}
