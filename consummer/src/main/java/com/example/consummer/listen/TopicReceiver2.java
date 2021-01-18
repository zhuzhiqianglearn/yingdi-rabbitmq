package com.example.consummer.listen;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.woman")
public class TopicReceiver2 {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("topic.woman  : " + testMessage.toString());
    }

}
